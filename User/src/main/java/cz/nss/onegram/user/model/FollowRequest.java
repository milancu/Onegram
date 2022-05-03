package cz.nss.onegram.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow_request")
@Getter
@Setter
@NoArgsConstructor
public class FollowRequest extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Override
    public String toString() {
        return "FollowRequest{" +
                "receiver=" + receiver +
                ", date=" + date +
                '}';
    }
}
