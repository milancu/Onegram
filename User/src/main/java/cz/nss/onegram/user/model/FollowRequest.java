package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FOLLOW_REQUEST")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FollowRequest extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @ManyToOne
    @JoinColumn(name="SENDER_ID")
    private User sender;

    @Column(name = "REQUEST_DATE", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Override
    public String toString() {
        return "FollowRequest{" +
                "receiver=" + receiver +
                ", sender=" + sender +
                ", date=" + date +
                '}';
    }
}
