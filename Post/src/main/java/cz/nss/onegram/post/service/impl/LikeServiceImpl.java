package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostRepository postRepository;

    @Override
    public void persist(Like like, Post post) {
        List<Like> likes = post.getLikes();

        boolean alreadyLiked = likes.stream()
                .map(Like::getAuthorId)
                .filter(authorId -> (authorId == like.getAuthorId()))
                .collect(Collectors.toList())
                .size() >= 1;

        if (!alreadyLiked){
            post.getLikes().add(like);
            postRepository.save(post);
        }
    }

    @Override
    public void delete(Like like, Post post) {
        if (post.getLikes().remove(like)){
            postRepository.save(post);
        }
    }
}
