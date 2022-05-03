package cz.nss.onegram.user.model;

import cz.nss.onegram.user.model.enums.FollowState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "follow_request")
@Getter
@Setter
@NoArgsConstructor
public class FollowRequest extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="RECEIVER_ID")
    private User receiver;

    @Column(name = "request_date", nullable = false)
    private LocalDate date;
}
