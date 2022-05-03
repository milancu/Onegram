package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.Message;

public interface MessageService {

    public Message findById(int id);
    public Message sendMessage(String message, int receiver_id);
    public int removeMessage(int id);
}
