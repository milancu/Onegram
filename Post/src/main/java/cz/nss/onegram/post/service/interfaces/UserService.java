package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetailsImpl getCurrentUser();

    public boolean userCreatedPost(String postId, UserDetailsImpl user);
}
