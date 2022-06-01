package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class SystemInitializerImpl implements SystemInitializer {

    private final UserServiceImpl userService;
    private final MessageRepository messageRepository;
    private final FollowRequestRepository followRequestRepository;
    private final Environment environment;

    private final String EMAIL_POSTFIX = "@fel.cvut.cz";

    private User cuphuon3;
    private User belkapre;
    private User bureson2;
    private User pivonja1;
    private User naplava;
    private User koci;
    private User sebekji1;
    private User nagyoing;

    @Value("${spring.cloud.azure.storage.blob.account-endpoint}")
    private String BASE_PHOTO_LINK;

    private final List<String> DEFAULT_PHOTO_NAMES = SystemInitializer.getDefaultPhotoNames();

    @Override
    @PostConstruct
    public void initSystem() {
        if (Arrays.asList(environment.getActiveProfiles()).contains("testprofile")) {
            log.info("Testprofile");
            return;
        }
        generateUser();
        generateFollowing();
        generateMessage();
    }

    public void generateUser() {
        log.info("Generating users.");

        cuphuon3 = User.builder()
                .email("cuphuon3" + EMAIL_POSTFIX)
                .username("cuphuon3")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Jsem nejhezci")
                .link("milancu.com")
                .image(BASE_PHOTO_LINK + "/" + DEFAULT_PHOTO_NAMES.get(1))
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(cuphuon3);

        belkapre = User.builder()
                .email("belkapre" + EMAIL_POSTFIX)
                .username("belkapre")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Dobre jak cyp hej")
                .link("github.com")
                .image(BASE_PHOTO_LINK + "/" + DEFAULT_PHOTO_NAMES.get(2))
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(belkapre);


        bureson2 = User.builder()
                .email("bureson2" + EMAIL_POSTFIX)
                .username("bureson2")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Sparta❤")
                .image(BASE_PHOTO_LINK + "/" + DEFAULT_PHOTO_NAMES.get(3))
                .link("sparta.cz")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(bureson2);


        pivonja1 = User.builder()
                .email("pivonja1" + EMAIL_POSTFIX)
                .username("pivonja1")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("127.0.0.1 Is the only place")
                .link("slavia.cz")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(pivonja1);


        naplava = User.builder()
                .email("naplava" + EMAIL_POSTFIX)
                .username("naplava")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .link("http://naplavovi.cz/")
                .isPublic(true)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(naplava);

        koci = User.builder()
                .email("koci" + EMAIL_POSTFIX)
                .username("koci")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .link("https://czm.fel.cvut.cz/cs/")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(koci);

        sebekji1 = User.builder()
                .email("sebekji1" + EMAIL_POSTFIX)
                .username("sebekji1")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .bio("Nemuzu rict neeee, ale")
                .link("https://czm.fel.cvut.cz/cs/")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(sebekji1);

        nagyoing = User.builder()
                .email("nagyoing" + EMAIL_POSTFIX)
                .username("nagyoing")
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .link("https://czm.fel.cvut.cz/cs/")
                .bio("Foo foo foo")
                .isPublic(false)
                .following(new ArrayList<>())
                .follower(new ArrayList<>())
                .build();
        userService.persist(nagyoing);


        log.info("users were generated.");
    }

    public void generateFollowing() {
        log.info("Generating following.");

        //Naplava sleduje Kociho
        koci.addFollower(naplava);
        naplava.addFollowing(koci);

        //Koci sleduje Naplavu
        naplava.addFollower(koci);
        koci.addFollowing(naplava);

        //Koci sleduje Sebka
        sebekji1.addFollower(koci);
        koci.addFollowing(sebekji1);

        //Koci sleduje Ondreje
        bureson2.addFollower(koci);
        koci.addFollowing(bureson2);

        //Sebek sleduje Nagyovou
        nagyoing.addFollower(sebekji1);
        sebekji1.addFollowing(nagyoing);

        //Nagyova sleduje Cua
        cuphuon3.addFollower(nagyoing);
        nagyoing.addFollowing(cuphuon3);

        //<editor-fold desc="Cu nekoho sleduje">
        //Cu sleduje Ondreje
        bureson2.addFollower(cuphuon3);
        cuphuon3.addFollowing(bureson2);

        //Cu sleduje Premka
        belkapre.addFollower(cuphuon3);
        cuphuon3.addFollowing(belkapre);

        //Cu sleduje Honzika
        pivonja1.addFollower(cuphuon3);
        cuphuon3.addFollowing(pivonja1);

        //Cu sleduje Naplavu
        naplava.addFollower(cuphuon3);
        cuphuon3.addFollowing(naplava);

        //Cu sleduje Kociho
        koci.addFollower(cuphuon3);
        cuphuon3.addFollowing(koci);

        //Cu sleduje Nagyovou
        nagyoing.addFollower(cuphuon3);
        cuphuon3.addFollowing(nagyoing);

        //Cu sleduje Sebka
        sebekji1.addFollower(cuphuon3);
        cuphuon3.addFollowing(sebekji1);
        //</editor-fold>

        //<editor-fold desc="Premek nekoho sleduje">
        //Premek sleduje Cua
        cuphuon3.addFollower(belkapre);
        belkapre.addFollowing(cuphuon3);

        //Premek sleduje Ondreje
        bureson2.addFollower(belkapre);
        belkapre.addFollowing(bureson2);

        //Premek sleduje Honzika
        pivonja1.addFollower(belkapre);
        belkapre.addFollowing(pivonja1);

        //Premek sleduje Nagyovou
        nagyoing.addFollower(belkapre);
        belkapre.addFollowing(nagyoing);
        //</editor-fold>

        //<editor-fold desc="Ondrej nekoho sleduje">
        //Ondrej sleduje Cua
        cuphuon3.addFollower(bureson2);
        bureson2.addFollowing(cuphuon3);

        //Ondrej sleduje Premka
        belkapre.addFollower(bureson2);
        bureson2.addFollowing(belkapre);

        //Ondrej sleduje Honzu
        pivonja1.addFollower(bureson2);
        bureson2.addFollowing(pivonja1);

        //Ondrej sleduje Naplavu
        naplava.addFollower(bureson2);
        bureson2.addFollowing(naplava);
        //</editor-fold>

        //<editor-fold desc="Honzik nekoho sleduje">
        //Honzik sleduje Cua
        cuphuon3.addFollower(pivonja1);
        pivonja1.addFollowing(cuphuon3);

        //Honzik sleduje Premka
        belkapre.addFollower(pivonja1);
        pivonja1.addFollowing(belkapre);

        //Honzik sleduje Ondreje
        bureson2.addFollower(pivonja1);
        pivonja1.addFollowing(bureson2);
        //</editor-fold>

        userService.persist(cuphuon3);
        userService.persist(belkapre);
        userService.persist(bureson2);
        userService.persist(pivonja1);
        userService.persist(naplava);
        userService.persist(koci);
        userService.persist(sebekji1);
        userService.persist(nagyoing);


        //Koci poslal zadost o sledovani Nagyove
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(koci)
                        .receiver(nagyoing)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );

        //Koci poslal zadost o sledovani Premka
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(koci)
                        .receiver(belkapre)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );

        //Sebek poslal zadost o sledovani Nagyove
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(sebekji1)
                        .receiver(nagyoing)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );

        //Nagyova poslala zadost o sledovani Sebkovi
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(nagyoing)
                        .receiver(sebekji1)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );

        //Nagyova poslala zadost o sledovani Premkovi
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(nagyoing)
                        .receiver(belkapre)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );

        //Naplava poslala zadost o sledovani Cua
        followRequestRepository.save(
                FollowRequest.builder()
                        .sender(naplava)
                        .receiver(cuphuon3)
                        .createdAtDate(LocalDate.now())
                        .createdAtTime(LocalTime.now())
                        .build()
        );


        log.info("following were generated.");
    }

    public void generateMessage() {
        log.info("Generating messages.");

        //<editor-fold desc="Konverzace ja a premda">
        messageRepository.save(
                Message.builder()
                        .message("Ahoj jak se mas")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(cuphuon3)
                        .receiver(belkapre)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Mam se dobre")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(belkapre)
                        .receiver(cuphuon3)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("To to rad slysim")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(cuphuon3)
                        .receiver(belkapre)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Co ty?")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(belkapre)
                        .receiver(cuphuon3)
                        .hasRead(false)
                        .isDeleted(false)
                        .build()
        );
        //</editor-fold>

        //<editor-fold desc="Konverzace Koci a Naplava">
        messageRepository.save(
                Message.builder()
                        .message("Dame jidlo?")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(koci)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Nemam moc hlad")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Nebud kyblik, dej si pytlik")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(koci)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Ti dam pytlik ty @$%*#")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(true)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Ale jo")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(naplava)
                        .receiver(koci)
                        .hasRead(true)
                        .isDeleted(true)
                        .build()
        );
        //</editor-fold>

        messageRepository.save(
                Message.builder()
                        .message("Ahoj")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(nagyoing)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Jak se mas")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(sebekji1)
                        .receiver(naplava)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        messageRepository.save(
                Message.builder()
                        .message("Dobre")
                        .sentAtDate(LocalDate.now())
                        .sentAtTime(LocalTime.now())
                        .sender(naplava)
                        .receiver(sebekji1)
                        .hasRead(true)
                        .isDeleted(false)
                        .build()
        );

        log.info("messages were generated.");

    }
}
