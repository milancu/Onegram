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

import java.time.LocalDateTime;
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
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void deleteAll(List<User> users) {

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
        User newUser = User.builder().created(LocalDateTime.now()).email(email).username(email) // TODO
                .follower(new ArrayList<>()).sentMessages(new ArrayList<>()).following(new ArrayList<>()).isPublic(true).build();

        userRepository.save(newUser);
        log.info("New user created {}", newUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) return null;
        else {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(email);
        }
    }

    @Override
    public User followUser(int userToFollow_id) {
        User currUser = getCurrentUser();
        User userToFollow = findById(userToFollow_id);

        if (userToFollow == null) {
            log.error("cannot find user");
            return null;
        }

        if (currUser.equals(userToFollow)) {
            log.warn("cannot follow yourself");
            return userToFollow;
        }

        if (userToFollow.isPublic()) {
            userToFollow.addFollower(currUser);
            currUser.addFollowing(userToFollow);
            log.info("follow: {}", userToFollow);
        } else {
            FollowRequest followRequest = new FollowRequest();
            followRequest.setReceiver(userToFollow);
            followRequestRepository.save(followRequest);
            log.info("create request: {}", followRequest);
        }
        return userToFollow;
    }

    @Override
    public void unFollowUser(int userToUnFollow_id) {
        User currUser = getCurrentUser();
        User userToUnFollow = findById(userToUnFollow_id);

        try {
            currUser.removeFollowing(userToUnFollow);
            log.info("unfollow user: {}", userToUnFollow);
        } catch (Exception e) {
            log.error("user was not found in list");//TODO
        }
    }
}
