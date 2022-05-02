package cz.nss.onegram.user.graphql.input.followRequest;

import lombok.Data;

@Data
public class CreateRequestInput {
    private int toUserId;
}
