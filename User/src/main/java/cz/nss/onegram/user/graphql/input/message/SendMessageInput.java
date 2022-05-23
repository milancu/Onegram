package cz.nss.onegram.user.graphql.input.message;

import lombok.Data;

@Data
public class SendMessageInput {
    private String message;
    private int receiver_id;
}
