package cz.nss.onegram.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversation_message")
@Getter
@Setter
@NoArgsConstructor
public class Message extends AbstractEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false) //TODO probably neco jineho nez name date
    private LocalDateTime date;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @ManyToOne //TODO prodiskutovat tohle reseni
    @JoinColumn(name="sender_id")
    private User sender;
}
