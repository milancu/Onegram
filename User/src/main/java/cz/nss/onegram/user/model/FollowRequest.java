package cz.nss.onegram.user.model;

import com.sun.istack.NotNull;
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

    @Column(name = "from_user", nullable = false)
    private int from;

    @Column(name = "request_date", nullable = false)
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_state", nullable = false)
    private FollowState followState;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
