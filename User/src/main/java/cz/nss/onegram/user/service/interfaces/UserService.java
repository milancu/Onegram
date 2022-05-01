package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User findById(String id);

    public User findByUsername(String username);

    public void deleteAll(List<User> users);

    public void persist(User user);
}
