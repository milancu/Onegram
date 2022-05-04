package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final PostService postService;

    public UserDetailsImpl getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            return (UserDetailsImpl) auth.getPrincipal();
        }

        return null;
    }

    /**
     * @return boolean true if user created the post
     */
    public boolean userCreatedPost(String postId, UserDetailsImpl user){
        log.info("Checking if user created a post.");
        Post post = postService.findById(postId);

        if (user == null){
            log.info("User is null.");
            return false;
        }

        return post == null || post.getAuthorId().equals(user.getId());
    }
}
