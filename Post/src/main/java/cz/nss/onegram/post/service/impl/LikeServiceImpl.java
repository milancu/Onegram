package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.exception.PostserviceException;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostRepository postRepository;

    @Override
    public void persist(Like like, Likeable likeable, Post post) {
        if (!postContainsLikeable(post, likeable)) throw new PostserviceException("Post: " + post.getId() + " Does not contain likeable: " + likeable.getId());

        if (likeableAlreadyLiked(likeable, like)) throw new PostserviceException("User id:" + like.getAuthorId() +  " has already liked this: " + likeable.getId());

        likeable.getLikes().add(like);
        postRepository.save(post);
    }

    /**
     * @return true if user has already liked a likeable
     */
    private boolean likeableAlreadyLiked(Likeable likeable, Like newLike){
        return likeable.getLikes().stream()
                .map(Like::getAuthorId)
                .filter(authorId -> (authorId == newLike.getAuthorId()))
                .collect(Collectors.toList())
                .size() >= 1;
    }

    private boolean postContainsLikeable(Post post, Likeable likeable){
        return post.getLikeables()
                .stream()
                .filter(l -> l.getId().equals(likeable.getId()))
                .collect(Collectors.toList())
                .size() >= 1;
    }

    @Override
    public Like findLikeByUser(Likeable likeable, UserDetailsImpl user) {
        return likeable.getLikes().stream()
                .filter(like -> like.getAuthorId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No like made by user: " + user.getUsername()  + " found for likeable id: " + likeable.getId() + ""));
    }

    @Override
    public void delete(Like like, Likeable likeable, Post post) {
        likeable.getLikes().remove(like);
        postRepository.save(post);
    }

    @Override
    public Likeable findLikeableById(String id, Post post) {
        return post.getLikeables()
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .get();
    }
}
