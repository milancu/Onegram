package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return null;
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
    public void unFollowUser(User user) {

    }
    @Override
    public void sendFollowRequest(User user) {

    }
}
