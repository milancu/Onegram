package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
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
import java.time.LocalTime;
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

    private final String LOREM1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    private final String LOREM2 = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium";

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

        for (int i = 1; i < 8; i++){
            generatePostsForAuthor(i, i + 4);
        }
    }

    private Post createPostNow(String description, Integer authorId){
        return createPost(description, authorId, LocalDate.now(), LocalTime.now());
    }

    private void generatePostsForAuthor(Integer authorId, Integer amount){
        for (int i = 0; i < amount; i++){
            Post post = createPost(LOREM1, authorId, getDateBasedOnAuthor(authorId, i), getTimeBasedOnAuthor(authorId, i));
            generateCommentsForPost(post, (amount + authorId) % 5);
        }
    }

    private void generateCommentsForPost(Post post, Integer amount){
        for (int i = 0; i < amount; i++){
            Comment comment = createComment(post, LOREM2, (((i + amount) * (i + amount)) % 7) + 1);
            generateSubcomments(post, comment, ((amount + i) * (amount + i)) % 4);
        }
    }

    private void generateSubcomments(Post post, Comment comment, Integer amount){
        for (int i = 0; i < amount; i++){
            SubComment subComment = createSubcomment(comment, post, LOREM2, (((i + amount) * (i + amount)) % 7) + 1);
        }
    }

    private LocalDate getDateBasedOnAuthor(Integer authorId, Integer postOrder){
        return LocalDate.of(2022, ((authorId + postOrder) % 5) + 1, ((authorId + postOrder) % 29) + 1);
    }

    private LocalTime getTimeBasedOnAuthor(Integer authorId, Integer postOrder){
        return LocalTime.of((authorId + postOrder) % 12, (authorId + postOrder) % 60, (authorId + postOrder) % 60);
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
                .imagePaths(List.of("https://nssonegram.blob.core.windows.net/images/" + SystemInitializer.getDefaultImageName()))
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

    private SubComment createSubcomment(Comment comment, Post post, String content, Integer authorId){
        SubComment subComment = SubComment.builder()
                .authorId(authorId)
                .content(content)
                .likes(new ArrayList<>())
                .build();

        commentService.persistSubComment(subComment, comment, post);
        log.info("Generated subcomment: {}", subComment);
        return subComment;
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
