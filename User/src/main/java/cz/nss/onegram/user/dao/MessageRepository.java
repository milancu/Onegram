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

    @Query(value = "SELECT message FROM Message message where message.receiver = :receiver_id")
    public List<Message> getAllMessageInConversation(@Param("receiver_id") int receiverID);
}
