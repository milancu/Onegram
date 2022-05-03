package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemInitializerImpl implements SystemInitializer {

    private final UserServiceImpl userService;
    private final MessageServiceImpl messageService;
    private final Random random = new Random();

    private final Environment environment;

    @Override
    @PostConstruct
    public void initSystem() {
        generateUser();
    }

    public void generateUser() {
        log.info("Generating users.");
        String[] usernames = {"cuphuon3", "belkapre", "bureson2", "pivonja1"};

        for (String username : usernames) {
            User user = new User();
            user.setUsername(username);
            String bio = username.equals("cuphuon3") ? "jsem nejhezci" : "ahoj";
            if (username.equals("cuphuon3")) user.setPublic(true);
            user.setBio(bio);
            user.setEmail(username + "@cvut.fel.cz");
            userService.persist(user);
        }
    }
}
