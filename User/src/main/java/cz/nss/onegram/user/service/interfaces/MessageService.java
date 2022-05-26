package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;

import java.util.List;

public interface MessageService {

    public Message findById(int id);
    public Message sendMessage(String message, int receiver_id);
    public void removeMessage(int id);
    public void makeMessageRead(int id);
    public void makeMessageUnread(int id);
    public List<Message> getAllMessageWithUser(int receiver_id);
    public List<Message> getLatestMessages();
    public boolean hasSentMessage(int id);
    public boolean hasReceivedMessage(int id);
}
