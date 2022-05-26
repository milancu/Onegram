package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ONEGRAM_USER")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDate createdAtDate = LocalDate.now();

    @Column(name = "CREATED_TIME", nullable = false)
    private LocalTime createdAtTime = LocalTime.now();

    @Column(name = "BIO")
    private String bio;

    @Column(name = "LINK")
    private String link;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "IS_PUBLIC")
    private boolean isPublic;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ONEGRAM_USER_FOLLOWING",
            joinColumns = @JoinColumn(name = "FOLLOWER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FOLLOWING_ID")
    )
    private List<User> following = new ArrayList<>();

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<User> follower = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAtDate=" + createdAtDate +
                ", createdAtTime=" + createdAtTime +
                ", bio='" + bio + '\'' +
                ", isPublic=" + isPublic +
                ", following=" + following.size() +
                ", follower=" + follower.size() +
                '}';
    }

    public void addFollower(User user) {
        follower.add(user);
    }

    public void removeFollower(User user) {
        follower.remove(user);
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    public void removeFollowing(User user) {
        following.remove(user);
    }
}
