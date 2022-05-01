package cz.nss.onegram.user.graphql.resolver;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserResolver implements GraphQLQueryResolver {
    private final UserService userService;

    public List<User> getUsers() {
        log.info("Getting all users.");
        return userService.getAllUsers();
    }

//    public void createUser(String username, String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(username + "@cvut.fel.cz");
//        user.setPassword("123456");
//        user.setCreated(LocalDateTime.now());
//
//        userService.persist(user);
//    }
}