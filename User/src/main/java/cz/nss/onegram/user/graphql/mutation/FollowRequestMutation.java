package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.followRequest.AcceptRequestInput;
import cz.nss.onegram.user.graphql.input.followRequest.CancelRequestInput;
import cz.nss.onegram.user.graphql.input.followRequest.CreateRequestInput;
import cz.nss.onegram.user.graphql.input.followRequest.RejectRequestInput;
import cz.nss.onegram.user.model.FollowRequest;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FollowRequestMutation implements GraphQLMutationResolver {

    public FollowRequest acceptRequest(AcceptRequestInput input) {
        return null;
    }

    public Integer cancelRequest(CancelRequestInput input) {
        return null;
    }

    public FollowRequest createRequest(CreateRequestInput input) {
        return null;
    }

    public FollowRequest rejectRequest(RejectRequestInput input) {
        return null;
    }
}
