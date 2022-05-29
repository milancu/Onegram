package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "MESSAGE")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message extends AbstractEntity {

    @Column(name = "CONTENT", nullable = false)
    private String message;

    @Column(name = "SENT_DATE", nullable = false)
    private LocalDate sentAtDate = LocalDate.now();

    @Column(name = "SENT_TIME", nullable = false)
    private LocalTime sentAtTime = LocalTime.now();

    @Column(name = "HAS_READ", nullable = false)
    private boolean hasRead = false;

    @Column(name = "IS_DELETED", nullable = false)
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
                ", sentAtDate=" + sentAtDate +
                ", sentAtTime=" + sentAtTime +
                ", hasRead=" + hasRead +
                ", isDeleted=" + isDeleted +
                ", receiver=" + receiver +
                ", sender=" + sender +
                '}';
    }
}
