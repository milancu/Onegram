package cz.nss.onegram.user;

import cz.nss.onegram.user.constants.KafkaConstants;
import cz.nss.onegram.user.model.MessageKafka;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile(value = {"!testprofile"})
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    UserService userService;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(MessageKafka message) {
        System.out.println(userService.getCurrentUser().getUsername());
        System.out.println(message.getSender());
        if (userService.getCurrentUser().getUsername().equals(message.getSender()) || userService.getCurrentUser().getUsername().equals(message.getReceiver())) {
            System.out.println("cau");
        }
        log.info("sending via kafka listener..");
        template.convertAndSend("/topic/group", message);
    }
}
