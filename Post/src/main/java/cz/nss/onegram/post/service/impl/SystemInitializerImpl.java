package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.interfaces.CommentService;
import cz.nss.onegram.post.service.interfaces.LikeService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.SystemInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemInitializerImpl implements SystemInitializer {
    private final PostService postService;

    private final PostRepository postRepository;

    private final CommentService commentService;

    private final Environment environment;

    private final LikeService likeService;

    private final static Random random = new Random();

    @Override
    @PostConstruct
    public void initSystem() {;
        postRepository.deleteAll(postService.findAll());
        log.info("All posts deleted!");

        if (Arrays.asList(environment.getActiveProfiles()).contains("testprofile")) {
            log.warn("System initializer stopped, testprofile active, no data will be generated.");
            return;
        }

        Post post1 = createPostNow("Přeji krásně pondělí!", 5);
        createPostNow("Přeji krásnou něděli!", 5);

        Comment comment = createComment(post1, "Koment pridal autor prispevku.", 1);
        createComment(post1, "Koment nepridal autor prispevku.", 2);

        createLike(post1, comment, 1);

        Post oldPost = createPost("Hodne stary post", 3,
                LocalDate.of(2000, 1, 1),
                LocalTime.of(10, 10));

        createComment(oldPost, "Hodne stary koment", 2);
    }

    private Post createPostNow(String description, Integer authorId){
        return createPost(description, authorId, LocalDate.now(), LocalTime.now());
    }

    private Post createPost(String description, Integer authorId, LocalDate date, LocalTime time){
        Post post = Post.builder()
                .authorId(authorId)
                .comments(new ArrayList<>())
                .createdAtDate(date)
                .createdAtTime(time)
                .description(description)
                .likes(new ArrayList<>())
                .tags(new ArrayList<>())
                .build();

        postRepository.save(post);
        log.info("Generated post: {}", post);
        return post;
    }

    private Comment createComment(Post post, String content, Integer authorId){
        Comment comment = Comment.builder()
                .authorId(authorId)
                .content(content)
                .likes(new ArrayList<>())
                .subComments(new ArrayList<>())
                .build();

        commentService.persist(comment, post);
        log.info("Generated comment: {}", comment);
        return comment;
    }

    private Like createLike(Post post, Likeable likeable, Integer authorId){
        Like like = Like.builder()
                .authorId(authorId)
                .build();

        log.info("Generated like: {} On likeable: {}", like, likeable);
        likeService.persist(like, likeable, post);
        return like;
    }
}
