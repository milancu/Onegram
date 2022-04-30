package cz.nss.onegram.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_conversation")
@Getter
@Setter
@NoArgsConstructor
public class Conversation extends AbstractEntity {

    @ManyToMany(mappedBy = "conversations")
    private List<User> members;


    @OneToMany
    @JoinTable(
            name = "conversation_messages",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messages;

}
