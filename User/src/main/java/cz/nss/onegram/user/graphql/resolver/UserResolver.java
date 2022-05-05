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

    public List<User> getUsers() {
        log.info("Getting all users.");
        return userService.getAllUsers();
    }

    public List<FollowRequest> getFollowRequests() {
        log.info("Getting all follow request");
        return userService.getAllReceivedFollowRequests();
    }

    public List<User> getFollowers() {
        log.info("Getting all followers");
        return userService.getFollowers();
    }

    public List<User> getFollowing() {
        log.info("Getting all followings");
        return userService.getFollowing();
    }
}