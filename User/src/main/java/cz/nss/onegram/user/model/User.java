package cz.nss.onegram.user.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "onegram_system_user") //TODO probably to bude jinak
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @Column(name = "bio")
    private String bio;

    @Column(name = "link")
    private String link; //TODO dunno co to znaci

    @Column(name = "image")
    private String image; //TODO predelat na filestystem path

    @Column(name = "isPublic")
    private boolean isPublic;

    @ManyToMany
    public List<Conversation> conversations;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages;


    @ManyToMany
    @JoinTable(
            name = "user_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers;

    @ManyToMany
    @JoinTable(
            name = "user_follower",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> followerOf;

}
