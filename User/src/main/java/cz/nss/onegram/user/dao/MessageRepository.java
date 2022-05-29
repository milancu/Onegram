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

    @Query(value = "SELECT message FROM Message message WHERE (message.receiver.id = :receiver_id AND message.sender.id = :sender_id) OR (message.receiver.id = :sender_id AND message.sender.id = :receiver_id) AND message.isDeleted = false order by message.sentAtDate desc, message.sentAtTime desc")
    List<Message> getAllMessageWithUser(@Param("receiver_id") int receiver_id, @Param("sender_id") int sender_id);

    @Query(value = "SELECT distinct message.receiver.id FROM Message message WHERE message.sender.id = :sender_id AND message.isDeleted = false")
    List<Integer> getConversationSent(@Param("sender_id") int sender_id); //TODO hnusne jak cyp, proste to vraci zpravy id distinct prijemcu

    @Query(value = "SELECT distinct message.sender.id FROM Message message WHERE message.receiver.id = :receiver_id AND message.isDeleted = false")
    List<Integer> getConversationReceived(@Param("receiver_id") int receiver_id); //TODO hnusne jak cyp, proste to vraci zpravy id distinct odesilatelu

}
