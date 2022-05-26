package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.message.DeleteMessageInput;
import cz.nss.onegram.user.graphql.input.message.MakeMessageReadInput;
import cz.nss.onegram.user.graphql.input.message.MakeMessageUnreadInput;
import cz.nss.onegram.user.graphql.input.message.SendMessageInput;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.service.interfaces.MessageService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageMutation implements GraphQLMutationResolver {

    final private MessageService messageService;

    public Message sendMessage(SendMessageInput input) {
        return messageService.sendMessage(input.getMessage(), input.getReceiver_id());
    }

    @PreAuthorize("@messageServiceImpl.hasSentMessage(#input.id)")
    public Integer deleteMessage(DeleteMessageInput input) {
        messageService.removeMessage(input.getId());
        return 1;
    }
    @PreAuthorize("@messageServiceImpl.hasReceivedMessage(#input.id)")
    public Integer makeMessageRead(MakeMessageReadInput input) {
        messageService.makeMessageRead(input.getId());
        return 1;
    }

    @PreAuthorize("@messageServiceImpl.hasReceivedMessage(#input.id)")
    public Integer makeMessageUnread(MakeMessageUnreadInput input) {
        messageService.makeMessageUnread(input.getId());
        return 1;
    }
}
