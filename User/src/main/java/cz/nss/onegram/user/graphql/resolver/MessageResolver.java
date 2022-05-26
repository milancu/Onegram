package cz.nss.onegram.user.graphql.resolver;

import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.service.interfaces.MessageService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageResolver implements GraphQLQueryResolver {

    private final MessageService messageService;

    public List<Message> getMessagesInConversation(int id) {
        return messageService.getAllMessageWithUser(id);
    }

    public List<Message> getLatestMessages() {
        return messageService.getLatestMessages();
    }
}
