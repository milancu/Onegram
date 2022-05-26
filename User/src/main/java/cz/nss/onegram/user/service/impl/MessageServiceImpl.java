package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.constants.KafkaConstants;
import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.exception.MessageServiceException;
import cz.nss.onegram.user.exception.UserServiceException;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.MessageKafka;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.MessageService;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    private KafkaTemplate<String, MessageKafka> kafkaTemplate;

    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message sendMessage(String message, int receiver_id) {
        if (message.trim().length() == 0) throw new MessageServiceException("Empty message");

        User currentUser = userService.getCurrentUser();
        User receiver = userService.findById(receiver_id);

        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setSender(currentUser);
        newMessage.setReceiver(receiver);

        messageRepository.save(newMessage);
        try {
            MessageKafka messageKafka = new MessageKafka();
            messageKafka.setMessage(message);
            messageKafka.setSender(currentUser.getUsername());
            messageKafka.setReceiver(receiver.getUsername());
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, messageKafka).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info("sent message: {}", newMessage);
        return newMessage;
    }

    @Override
    public void removeMessage(int id) {
        User currentUser = userService.getCurrentUser();
        Message messageToDelete = findById(id);

        if (messageToDelete == null) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }
        if (!messageToDelete.getSender().equals(currentUser)) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }

        messageToDelete.setDeleted(true);
        messageRepository.save(messageToDelete);
        log.info("deleted message: {}", messageToDelete);
    }

    @Override
    public void makeMessageRead(int id) {
        Message message = findById(id);
        User currentUser = userService.getCurrentUser();

        if (message == null) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }

        if (!message.getReceiver().equals(currentUser)) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }

        message.setHasRead(true);
        messageRepository.save(message);
        log.info("Read message: {}", message);
    }

    @Override
    public void makeMessageUnread(int id) {
        Message message = findById(id);
        User currentUser = userService.getCurrentUser();

        if (message == null) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }

        if (!message.getReceiver().equals(currentUser)) {
            log.error("Message does not exists");
            throw new MessageServiceException("Message does not exists");
        }

        message.setHasRead(false);
        messageRepository.save(message);
        log.info("Unread message: {}", message);
    }

    @Override
    public List<Message> getAllMessageWithUser(int receiver_id) {
        if (userService.findById(receiver_id) == null) throw new UserServiceException("User does not exists.");
        List<Message> m = messageRepository.getAllMessageWithUser(receiver_id, userService.getCurrentUser().getId());
        for (Message message : messageRepository.getAllMessageWithUser(userService.getCurrentUser().getId(), receiver_id)) {
            if (!m.contains(message)) m.add(message);
        }
        return m;
    }

    @Override
    public List<Message> getLatestMessages() {
        List<Integer> conversation = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        User currUser = userService.getCurrentUser();
        int id = currUser.getId();


        messageRepository.getConversationSent(id).forEach(x -> {
            if (x != id && !conversation.contains(x)) conversation.add(x);
        });
        messageRepository.getConversationReceived(id).forEach(x -> {
            if (x != id && !conversation.contains(x)) conversation.add(x);
        });

        for (Integer x : conversation) {
            messages.add(messageRepository.getAllMessageWithUser(id, x).get(0));
        }
        return messages;
    }

    @Override
    public boolean hasSentMessage(int id) {
        log.debug("Checking if user sent a message.");
        return findById(id).getSender().equals(userService.getCurrentUser());
    }

    @Override
    public boolean hasReceivedMessage(int id) {
        log.debug("Checking if user received a message.");
        return findById(id).getReceiver().equals(userService.getCurrentUser());
    }
}
