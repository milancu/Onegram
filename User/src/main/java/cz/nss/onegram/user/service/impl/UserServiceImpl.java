package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FollowRequestRepository followRequestRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void persist(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void persist(OAuth2User user) {
        String email = user.getAttribute("email");
        User newUser = User.builder().createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now()).email(email).username(email) // TODO
                .follower(new ArrayList<>()).following(new ArrayList<>()).isPublic(true).build();

        userRepository.save(newUser);
        log.info("New user created {}", newUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) return null;
        else {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            //Pouze pro testing
            if (!email.contains("@")) {
                return userRepository.findByEmail(email.concat("@fel.cvut.cz"));
            }
            return userRepository.findByEmail(email);
        }
    }

    @Override
    public User followUser(int userToFollowId) {
        User currUser = getCurrentUser();
        User userToFollow = findById(userToFollowId);

        /**
         * if userToFollow is null then return exception //TODO
         */
        if (userToFollow == null) {
            log.error("cannot find user");
            return currUser;
        }

        /**
         * return false if userToFollow is equal as currentUser
         */
        if (currUser.equals(userToFollow)) {
            log.warn("cannot follow yourself");
            return currUser;
        }

        /**
         * if profile is public create follow, if is private create follow request, validation for existing request
         */
        if (userToFollow.isPublic()) {
            if (currUser.getFollowing().contains(userToFollow)) {
                log.warn("User has been already followed");
                return userToFollow;
            }
            userToFollow.addFollower(currUser);
            currUser.addFollowing(userToFollow);
            persist(userToFollow);
            persist(currUser);
            log.info("follow: {}", userToFollow);
        } else {
            if (followRequestRepository.getRequestToUser(currUser.getId(), userToFollowId) == null) {
                FollowRequest followRequest = new FollowRequest();
                followRequest.setReceiver(userToFollow);
                followRequest.setSender(currUser);
                followRequestRepository.save(followRequest);

                log.info("create request: {}", followRequest);
            } else {
                log.warn("Request already sent");
            }
        }
        return userToFollow;
    }

    @Override
    public void unFollowUser(int userToUnFollowId) {//TODO mozna predelat mapping, je to docela zbytecne to mapovat pres following a follower
        User currUser = getCurrentUser();
        User userToUnFollow = findById(userToUnFollowId);

        if (currUser.getFollowing().contains(userToUnFollow)) {
            userToUnFollow.removeFollower(currUser); //TODO validace na not exists in list
            currUser.removeFollowing(userToUnFollow);

            userRepository.save(userToUnFollow);
            userRepository.save(currUser);

            log.info("unfollowed User: {}", userToUnFollow);
            return;
        }
        log.warn("User already unfollowed");
    }

    @Override
    public void removeFollower(int userId) { //TODO execption
        User follower = findById(userId);
        if (follower == null) {
//            throw new ("user does not exists");
            log.error("User does not exists");
        } else {
            getCurrentUser().removeFollower(follower);
            userRepository.save(getCurrentUser());

            follower.removeFollowing(getCurrentUser());
            userRepository.save(follower);

            log.info("Remove follower: {}", follower);
        }
    }

    @Override
    public void acceptRequest(int requestId) {
        User currentUser = getCurrentUser();

        FollowRequest followRequest = followRequestRepository.findById(requestId);
        followRequest.getSender().addFollowing(currentUser);
        currentUser.addFollower(followRequest.getSender());

        userRepository.save(followRequest.getSender());
        userRepository.save(currentUser);

        followRequestRepository.delete(followRequest);

        log.info("Accepted follow request: {}", followRequest);
    }

    @Override
    public void rejectRequest(int requestId) {
        FollowRequest followRequest = followRequestRepository.findById(requestId);
        followRequestRepository.delete(followRequest);

        log.info("Rejected follow request: {}", followRequest);
    }

    @Override
    public List<User> getFollowing() {
        return getCurrentUser().getFollowing();
    }

    @Override
    public List<User> getFollowers() {
        return getCurrentUser().getFollower();
    }

    @Override
    public List<User> getFollowing(int userID) {
        return userRepository.findById(userID).get().getFollowing();
    }

    @Override
    public List<User> getFollowers(int userID) {
        return userRepository.findById(userID).get().getFollower();
    }

    @Override
    public void makeProfilePrivate() {
        getCurrentUser().setPublic(false);
    }

    @Override
    public void makeProfilePublic() {
        getCurrentUser().setPublic(true);
    }

    @Override
    public List<FollowRequest> getAllReceivedFollowRequests() {
        return followRequestRepository.getAllReceivedRequestOfUser(getCurrentUser().getId());
    }

    @Override
    public void editBio(String bio) {
        getCurrentUser().setBio(bio);
        userRepository.save(getCurrentUser());
        log.info("Change bio: {}", getCurrentUser());
    }
}
