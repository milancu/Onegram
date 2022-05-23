package cz.nss.onegram.user.dao;

import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT message FROM Message message WHERE message.receiver.id = :receiver_id AND message.sender.id = :sender_id AND message.isDeleted = false")
    List<Message> getAllMessageWithUser(@Param("receiver_id") int receiver_id, @Param("sender_id") int sender_id);
}
