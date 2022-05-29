package cz.nss.onegram.post.service;

import com.mongodb.assertions.Assertions;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.CommentService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import environment.Generator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Test
    public void userCreatedPost_createPostByUser_true(){
        Post post = Generator.generateRandomPost(10);
        postService.persist(post);
        UserDetailsImpl userMock = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(userMock.getId()).thenReturn(10);

        boolean result = userService.userCreatedPost(post.getId(), userMock);

        Assertions.assertTrue(result);
    }

    @Test
    public void userCreatedPost_createPostByDifferentUser_false(){
        Post post = Generator.generateRandomPost(100);
        postService.persist(post);
        UserDetailsImpl userMock = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(userMock.getId()).thenReturn(10);

        boolean result = userService.userCreatedPost(post.getId(), userMock);

        Assertions.assertFalse(result);
    }

    @Test
    public void userCreatedComment_createCommentByUser_true(){
        Post post = Generator.generateRandomPost(100);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(10);
        commentService.persist(comment, post);

        UserDetailsImpl userMock = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(userMock.getId()).thenReturn(10);

        boolean result = userService.userCreatedComment(post.getId(), comment.getId(), userMock);

        Assertions.assertTrue(result);
    }

    @Test
    public void userCreatedComment_createCommentByDifferentUser_false(){
        Post post = Generator.generateRandomPost(100);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(200);
        commentService.persist(comment, post);

        UserDetailsImpl userMock = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(userMock.getId()).thenReturn(10);

        boolean result = userService.userCreatedComment(post.getId(), comment.getId(), userMock);

        Assertions.assertFalse(result);
    }
}
