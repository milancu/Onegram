package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.user.AcceptRequestInput;
import cz.nss.onegram.user.graphql.input.user.FollowUserInput;
import cz.nss.onegram.user.graphql.input.user.RejectRequestInput;
import cz.nss.onegram.user.graphql.input.user.UnFollowUserInput;
import cz.nss.onegram.user.model.User;
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

    public User followUser(FollowUserInput input) {
        return userService.followUser(input.getUserId());
    }

    public User unFollowUser(UnFollowUserInput input) {
        userService.unFollowUser(input.getUserId());
        return null;
    }

    public Integer acceptRequest(AcceptRequestInput input) {
        userService.acceptRequest(input.getRequestId());
        return 1;
    }

    public Integer rejectRequest(RejectRequestInput input) {
        userService.rejectRequest(input.getRequestId());
        return 1;
    }
}
