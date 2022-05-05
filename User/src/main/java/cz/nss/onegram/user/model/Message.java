package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message extends AbstractEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "has_read", nullable = false)
    private boolean hasRead = false;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private User sender;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", date=" + date +
                ", hasRead=" + hasRead +
                ", isDeleted=" + isDeleted +
                ", receiver=" + receiver +
                ", sender=" + sender +
                '}';
    }
}
