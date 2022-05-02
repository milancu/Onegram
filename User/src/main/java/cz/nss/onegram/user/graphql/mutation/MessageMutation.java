package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.message.DeleteMessageInput;
import cz.nss.onegram.user.graphql.input.message.SendMessageInput;
import cz.nss.onegram.user.model.Message;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageMutation implements GraphQLMutationResolver {

    public Message sendMessage(SendMessageInput input) {
        return null;
    }

    public Integer deleteMessage(DeleteMessageInput input) {
        return null;
    }
}
