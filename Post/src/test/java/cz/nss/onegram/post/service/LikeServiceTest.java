package cz.nss.onegram.post.service;

import cz.nss.onegram.post.exception.PostserviceException;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.impl.CommentServiceImpl;
import cz.nss.onegram.post.service.impl.LikeServiceImpl;
import cz.nss.onegram.post.service.impl.PostServiceImpl;
import cz.nss.onegram.post.service.interfaces.CommentService;
import environment.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.NoSuchElementException;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class LikeServiceTest {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private LikeServiceImpl likeService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentServiceImpl commentService;

    @BeforeEach
    public void removeData(){
        postRepository.deleteAll(postService.findAll());
    }

    @Test
    public void persists_twoLikesFromSameUser_exceptionThrown(){
        Post post = Generator.generateRandomPost(2);
        Like like1 = Like.builder().authorId(5).build();
        Like like2 = Like.builder().authorId(5).build();

        postService.persist(post);
        likeService.persist(like1, post, post);
        Assertions.assertThrows(PostserviceException.class, () -> likeService.persist(like2, post, post));
    }

    @Test
    public void findLikeByUser_likeCreated_LikeFound(){
        Post post = Generator.generateRandomPost(2);
        Like like1 = Like.builder().authorId(5).build();
        postService.persist(post);
        likeService.persist(like1, post, post);
        UserDetailsImpl mockedUser = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(mockedUser.getId())
                        .thenReturn(5);

        Like result = likeService.findLikeByUser(post, mockedUser);

        Assertions.assertEquals(like1, result);
    }

    @Test
    public void delete_likeOnCommentCreatedAndDeleted_noLikeFound(){
        Post post = Generator.generateRandomPost(2);
        Like like1 = Like.builder().authorId(5).build();
        Comment comment = Generator.generateRandomComment(1);
        postService.persist(post);
        commentService.persist(comment, post);
        likeService.persist(like1, comment, post);
        UserDetailsImpl mockedUser = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(mockedUser.getId())
                .thenReturn(5);

        likeService.delete(like1, comment, post);

        Assertions.assertThrows(NoSuchElementException.class, () -> likeService.findLikeByUser(comment,mockedUser));
    }
}
