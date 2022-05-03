package cz.nss.onegram.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message extends AbstractEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false) //TODO probably neco jineho nez name date
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "has_read", nullable = false)
    private boolean hasRead = false;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name="RECEIVER_ID")
    private User receiver;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", date=" + date +
                ", hasRead=" + hasRead +
                ", isDeleted=" + isDeleted +
                ", receiver=" + receiver +
                '}';
    }
}
