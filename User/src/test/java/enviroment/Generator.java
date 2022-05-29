package enviroment;

import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Generator {

    private final String EMAIL_POSTFIX = "@fel.cvut.cz";

    public User generateCuphuon3() {
        return User.builder()
                .email("cuphuon3" + EMAIL_POSTFIX)
                .username("cuphuon3")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Jsem nejhezci")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateBelkapre() {
        return User.builder()
                .email("belkapre" + EMAIL_POSTFIX)
                .username("belkapre")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Dobre jak cyp hej")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateBureson2() {
        return User.builder()
                .email("bureson2" + EMAIL_POSTFIX)
                .username("bureson2")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Sparta❤")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generatePinvonja1() {
        return User.builder()
                .email("pivonja1" + EMAIL_POSTFIX)
                .username("pivonja1")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("127.0.0.1 Is the only place")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateNaplava() {
        return User.builder()
                .email("naplava" + EMAIL_POSTFIX)
                .username("naplava")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateKoci() {
        return User.builder()
                .email("koci" + EMAIL_POSTFIX)
                .username("koci")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateSebek() {
        return User.builder()
                .email("sebekji1" + EMAIL_POSTFIX)
                .username("sebekji1")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Nemuzu rict neeee, ale")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public User generateNagyova() {
        return User.builder()
                .email("nagyoing" + EMAIL_POSTFIX)
                .username("nagyoing")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Foo foo foo")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
    }

    public Message generateMessage(User sender, User receiver, String message) {
        return Message.builder()
                .message(message)
                .sentAtDate(LocalDate.now())
                .sentAtTime(LocalTime.now())
                .sender(sender)
                .receiver(receiver)
                .hasRead(true)
                .isDeleted(false)
                .build();
    }
}
