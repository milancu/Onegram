package cz.nss.onegram.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conversation")
@Getter
@Setter
@NoArgsConstructor
public class Conversation extends AbstractEntity {

    @ManyToMany(mappedBy = "conversations")
    private List<User> members;


    @OneToMany
    @JoinTable(
            name = "conversation_message",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messages;

}
