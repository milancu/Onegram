package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.InputStream;
import java.util.List;

public interface UserService {

    public User findById(int id);

    public User findByEmail(String email);

    public List<User> findAll();

    public void persist(User user);

    public void persist(OAuth2User user);

    public User getCurrentUser();

    public void followUser(int userToFollowId);

    public void unFollowUser(int userToUnFollowId);

    public void removeFollower(int userId);

    public void acceptRequest(int requestId);

    public void rejectRequest(int requestId);

    public List<User> getFollowing();

    public List<User> getFollowers();

    public List<User> getFollowing(int userID);

    public List<User> getFollowers(int userID);

    public void makeProfilePrivate();

    public void makeProfilePublic();

    public List<FollowRequest> getAllReceivedFollowRequests();

    public void editBio(String bio);

    public void editUsername(String username);

    public void editLink(String link);

    public void addPhoto(InputStream file);

    public boolean hasSentRequest(int requestID);

    public boolean hasReceivedRequest(int requestID);
}
