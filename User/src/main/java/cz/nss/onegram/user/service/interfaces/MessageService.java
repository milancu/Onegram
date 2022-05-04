package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.Message;

import java.util.List;

public interface MessageService {

    public Message findById(int id);
    public Message sendMessage(String message, int receiver_id);
    public int removeMessage(int id);
    public Message makeMessageRead(int id);
    public Message makeMessageUnread(int id);
    public List<Message> getAllMessageInConversation(int id);
}
