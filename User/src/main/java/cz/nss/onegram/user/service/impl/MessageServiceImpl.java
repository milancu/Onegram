package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.MessageService;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message sendMessage(String message, int receiver_id) {

        //TODO na validaci uzivatele, mozna udelat validaci na public profil
        User currentUser = userService.getCurrentUser();
        User receiver = userService.findById(receiver_id);

        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setReceiver(receiver);

        messageRepository.save(newMessage);

        currentUser.sendMessage(newMessage);
        receiver.receivedMessage(newMessage);

        userRepository.save(currentUser);
        userRepository.save(receiver);

        log.info("sent message: {}", newMessage);

        return newMessage;
    }

    @Override
    public int removeMessage(int id) {

        User currentUser = userService.getCurrentUser();
        Message messageToDelete = findById(id);

        if (messageToDelete == null) {
            log.error("message does not exists");
            return 0;
        }

        if (currentUser.getSentMessages().contains(messageToDelete) && !messageToDelete.isDeleted()) {
            messageToDelete.setDeleted(true); //Nebude tim padem potreba kdyz odebiram z listu
            messageRepository.save(messageToDelete); //Muzu i rovnou delete, ale pry se to tak nerobi

            currentUser.removeMessage(messageToDelete);
            userRepository.save(currentUser);

            log.info("deleted message: {}", messageToDelete);
            return 1;
        }
        return 0;
    }

    @Override
    public Message makeMessageRead(int id) {
        Message message = findById(id);
        User current = userService.getCurrentUser();

        if (message == null) {
            log.error("message does not exists");
            return null;
        };

        if (current.getReceivedMessages().contains(message) && !message.isDeleted()) {
            message.setHasRead(true);
            messageRepository.save(message);
            return message;
        }

        return null;
    }

    @Override
    public Message makeMessageUnread(int id) {
        Message message = findById(id);
        User current = userService.getCurrentUser();

        if (message == null) {
            log.error("message does not exists");
            return null;
        };

        if (current.getReceivedMessages().contains(message) && !message.isDeleted()) {
            message.setHasRead(false);
            messageRepository.save(message);
            return message;
        }

        return null;
    }
}
