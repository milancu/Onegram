package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.User;
import cz.nss.onegram.post.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            return (User) auth.getPrincipal();
        }

        return null;
    }

    /**
     * @return boolean true if user created the post
     */
    public boolean userCreatedPost(Post post, User user){
        return Objects.equals(post.getAuthorId(), user.getId());
    }
}
