package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.message.DeleteMessageInput;
import cz.nss.onegram.user.graphql.input.message.SendMessageInput;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.service.interfaces.MessageService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageMutation implements GraphQLMutationResolver {

    final private MessageService messageService;

    public Message sendMessage(SendMessageInput input) {
        return messageService.sendMessage(input.getMessage(), input.getReceiver_id());
    }

    public Integer deleteMessage(DeleteMessageInput input) {
        return 1;
    }
}
