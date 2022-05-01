package cz.nss.onegram.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "system_user") //TODO probably to bude jinak
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "bio")
    private String bio;

    @Column(name = "link")
    private String link; //TODO dunno co to znaci

    @Column(name = "image")
    private String image; //TODO predelat na filestystem path

    @Column(name = "isPublic")
    private boolean isPublic;

    @ManyToMany
    @JoinTable(
            name = "system_user_conversation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    public List<Conversation> conversations;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages;

    @ManyToMany
    @JoinTable(
            name = "system_user_following",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> follower;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", bio='" + bio + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}
