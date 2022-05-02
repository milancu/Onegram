package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void deleteAll(List<User> users) {
    }

    @Override
    public void persist(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void persist(OAuth2User user) {
        String email = user.getAttribute("email");
        User newUser = User.builder()
                .created(LocalDateTime.now())
                .email(email)
                .username(email) // TODO
                .follower(new ArrayList<>())
                .messages(new ArrayList<>())
                .conversations(new ArrayList<>())
                .following(new ArrayList<>())
                .isPublic(true)
                .build();

        userRepository.save(newUser);
        log.info("New user created {}", newUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null) return null;

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            OAuth2User client = token.getPrincipal();
            String email = client.getAttribute("email");
            return this.findByEmail(email);
        } else {
            return null;
        }
    }
}
