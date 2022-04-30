package cz.nss.onegram.user.graphql.resolver;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserResolver implements GraphQLResolver<User> {
    private final UserService userService;

    public List<User> getAllUsers() {
        log.info("Getting all posts.");
        return userService.getAllUsers();
    }
}