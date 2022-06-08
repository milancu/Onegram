package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.exception.UserServiceException;
import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.FileService;
import cz.nss.onegram.user.service.interfaces.SystemInitializer;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
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

    private final FileService fileService;

    @Value("${storage.defaultprofilephoto}")
    private String DEFAULT_PHOTO_LINK;

    @Override
    public User findById(int id) {
        if (userRepository.findById(id).isEmpty()) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }
        return userRepository.findById(id).get();
    }

    @Override
    public void persist(User user) {
        if (user.getImage() == null) {
            log.info("Setting default image for user: {}, while persisting.", user.getUsername());
            user.setImage(DEFAULT_PHOTO_LINK);
        }
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void persist(OAuth2User user) {
        String email = user.getAttribute("email");
        User newUser = User.builder().createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now()).email(email).username(email) // TODO
                .link("")
                .bio("")
                .image(DEFAULT_PHOTO_LINK)
                .follower(new ArrayList<>()).following(new ArrayList<>()).isPublic(true).build();

        userRepository.save(newUser);
        log.info("New user created {}", newUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        } else {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByUsername(username);
        }
    }

    @Override
    public void followUser(int userToFollowId) {
        User currUser = getCurrentUser();
        User userToFollow = findById(userToFollowId);

        /**
         * if userToFollow is null then throw exception
         */
        if (userToFollow == null) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }

        /**
         * throw exception if userToFollow is equal as currentUser
         */
        if (currUser.equals(userToFollow)) {
            log.warn("Cannot follow yourself");
            throw new UserServiceException("Cannot follow yourself");
        }

        /**
         * if profile is public create follow, if is private create follow request, validation for existing request
         */
        if (userToFollow.isPublic()) {
            if (currUser.getFollowing().contains(userToFollow)) {
                log.warn("User is already being followed");
                throw new UserServiceException("User is already being followed");
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
    }

    @Override
    public void unFollowUser(int userToUnFollowId) {
        User currUser = getCurrentUser();
        User userToUnFollow = findById(userToUnFollowId);

        if (userToUnFollow == null) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }

        if (currUser.getFollowing().contains(userToUnFollow)) {
            userToUnFollow.removeFollower(currUser);
            currUser.removeFollowing(userToUnFollow);

            userRepository.save(userToUnFollow);
            userRepository.save(currUser);

            log.info("unfollowed User: {}", userToUnFollow);
        } else {
            log.warn("User is no longer followed");
            throw new UserServiceException("User is no longer followed");
        }
    }

    @Override
    public void removeFollower(int userId) {
        User follower = findById(userId);

        if (follower == null) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }

        if (getCurrentUser().getFollower().contains(follower)) {
            getCurrentUser().removeFollower(follower);
            userRepository.save(getCurrentUser());

            follower.removeFollowing(getCurrentUser());
            userRepository.save(follower);

            log.info("Remove follower: {}", follower);

        } else {
            log.warn("User is no longer following You");
            throw new UserServiceException("User is no longer following You");
        }
    }

    @Override
    public void acceptRequest(int requestId) {
        User currentUser = getCurrentUser();

        FollowRequest followRequest = followRequestRepository.findById(requestId);

        if (followRequest == null) {
            log.error("No such follow request was found");
            throw new UserServiceException("No such follow request was found");
        }

        if (followRequest.getReceiver().equals(currentUser)) {
            followRequest.getSender().addFollowing(currentUser);
            currentUser.addFollower(followRequest.getSender());

            userRepository.save(followRequest.getSender());
            userRepository.save(currentUser);

            followRequestRepository.delete(followRequest);
            log.info("Accepted follow request: {}", followRequest);
        } else {
            log.error("No such follow request was found");
            throw new UserServiceException("No such follow request was found");
        }
    }

    @Override
    public void rejectRequest(int requestId) {
        FollowRequest followRequest = followRequestRepository.findById(requestId);

        if (followRequest == null) {
            log.error("No such follow request was found");
            throw new UserServiceException("No such follow request was found");
        }

        if (followRequest.getReceiver().equals(getCurrentUser())) {
            followRequestRepository.delete(followRequest);
            log.info("Rejected follow request: {}", followRequest);
        } else {
            log.error("No such follow request was found");
            throw new UserServiceException("No such follow request was found");
        }
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
        if (findById(userID) == null) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }
        return findById(userID).getFollowing();
    }

    @Override
    public List<User> getFollowers(int userID) {
        if (findById(userID) == null) {
            log.error("User does not exists");
            throw new UserServiceException("User does not exists");
        }
        return findById(userID).getFollower();
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

    @Override
    public void editUsername(String username) {

        if (userRepository.findByUsername(username) != null) {
            log.error("Username already exists");
            throw new UserServiceException("Username already exists");
        }

        User user = getCurrentUser();
        user.setUsername(username);
        persist(user);

        log.info("Set new username:{}", user);
    }

    @Override
    public void editLink(String link) {

        User user = getCurrentUser();
        user.setLink(link);
        persist(user);

        log.info("Set new link:{}", user);
    }

    @Override
    public void addPhoto(InputStream file) {
        User user = getCurrentUser();
        String newImageUrl = fileService.saveAsPngFile(file);
        log.info("Saved new profile photo for user id: {} ", user.getId());

        String oldImageUrl = user.getImage();
        if (oldImageUrl != null && !SystemInitializer.isDefaultPhotoName(fileService.extractFilenameFromPath(oldImageUrl))) {
            // TODO check if file exists, FileService implementation
            fileService.deleteFile(fileService.extractFilenameFromPath(oldImageUrl));
            log.info("Deleted old profile photo of user id: {}", user.getId());
        }

        user.setImage(newImageUrl);
        userRepository.save(user);
    }

    @Override
    public boolean hasSentRequest(int requestID) {
        log.debug("Checking if user sent a request.");
        return followRequestRepository.findById(requestID).getSender().equals(getCurrentUser());
    }

    @Override
    public boolean hasReceivedRequest(int requestID) {
        log.debug("Checking if user received a request.");
        return followRequestRepository.findById(requestID).getReceiver().equals(getCurrentUser());
    }
}
