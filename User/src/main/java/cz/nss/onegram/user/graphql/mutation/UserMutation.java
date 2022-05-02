package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.user.FollowUserInput;
import cz.nss.onegram.user.graphql.input.user.UnFollowUserInput;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.FollowRequestService;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;
    private final FollowRequestService followRequestService;

    public void followUser(FollowUserInput input) {
        User currUser = userService.getCurrentUser();
        User userToFollow = userService.findById(input.getUserId());
        if (userToFollow.isPublic()) {
            userToFollow.addFollower(currUser);
            currUser.addFollowing(userToFollow);
            log.info("follow {}", userToFollow);
        } else {
            followRequestService.sendFollowRequest(currUser, userToFollow);
        }
    }

    public void unFollowUser(UnFollowUserInput input) {
        User currUser = userService.getCurrentUser();
        User userToUnFollow = userService.findById(input.getUserId());
        userToUnFollow.removeFollower(currUser);
        currUser.addFollowing(userToUnFollow);
    }
}
