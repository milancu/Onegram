package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.User;

public interface UserService {
    public User getCurrentUser();

    public boolean userCreatedPost(Post post, User user);
}
