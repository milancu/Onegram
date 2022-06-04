package cz.nss.onegram.user.graphql.resolver;

import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserResolver implements GraphQLQueryResolver {
    private final UserService userService;

    public User getUser(int userId) {
        log.info("Getting user.");
        return userService.findById(userId);
    }

    public List<User> getUsers(){
        log.info("Getting users");
        return userService.findAll();
    }

    public User getMy() {
        log.info("Getting user.");
        return userService.getCurrentUser();
    }

    public List<FollowRequest> getFollowRequests() {
        log.info("Getting all follow request");
        return userService.getAllReceivedFollowRequests();
    }

    public List<User> getMyFollowers() {
        log.info("Getting all followers");
        return userService.getFollowers();
    }

    public List<User> getMyFollowing() {
        log.info("Getting all followings");
        return userService.getFollowing();
    }

    public List<User> getFollowers(int user_id) {
        log.info("Getting all followers");
        return userService.getFollowers(user_id);
    }

    public List<User> getFollowing(int user_id) {
        log.info("Getting all followings");
        return userService.getFollowing(user_id);
    }
}