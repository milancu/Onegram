package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @JoinColumn(name = "SENDER_ID")
    private User sender;

    @Column(name = "REQUEST_DATE", nullable = false)
    private LocalDate createdAtDate = LocalDate.now();

    @Column(name = "REQUEST_TIME", nullable = false)
    private LocalTime createdAtTime = LocalTime.now();

    @Override
    public String toString() {
        return "FollowRequest{" +
                "receiver=" + receiver +
                ", sender=" + sender +
                ", createdAtDate=" + createdAtDate +
                ", createdAtTime=" + createdAtTime +
                '}';
    }
}
