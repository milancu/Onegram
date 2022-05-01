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
public class FollowRequest extends AbstractEntity {//TODO prodiskutovat zadatele a druhou stranu

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="to_user")
    private User toUser;

    @Column(name = "request_date", nullable = false)
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_state", nullable = false)
    private FollowState followState;
}
