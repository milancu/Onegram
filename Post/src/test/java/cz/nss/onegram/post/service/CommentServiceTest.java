package cz.nss.onegram.post.service;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.impl.CommentServiceImpl;
import cz.nss.onegram.post.service.impl.LikeServiceImpl;
import cz.nss.onegram.post.service.impl.PostServiceImpl;
import environment.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CommentServiceTest {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void removeData(){
        postRepository.deleteAll(postService.findAll());
    }

    @Test
    public void persist_regularComment_commentPersisted(){
        Post post = Generator.generateRandomPost(1);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(1);

        commentService.persist(comment, post);

        Comment result = postService.findAll().get(0).getComments().get(0);
        Assertions.assertEquals(result.getContent(), comment.getContent());
    }

    @Test
    public void delete_persistCommentAndDelete_0commentsOnPost(){
        Post post = Generator.generateRandomPost(1);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(1);
        commentService.persist(comment, post);

        commentService.delete(comment, post);

        Assertions.assertEquals(0, post.getComments().size());
    }

    @Test
    public void persistSubcomment_regularComment_subcommentPersisted(){
        Post post = Generator.generateRandomPost(1);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(1);
        commentService.persist(comment, post);
        SubComment subComment = Generator.generateRandomSubComment(1);

        commentService.persistSubComment(subComment, comment, post);

        SubComment result = commentService.findSubCommentById(subComment.getId(), post);
        Assertions.assertNotNull(result);
    }

    @Test
    public void delete_persistSubCommentAndDelete_subCommentNotFound(){
        Post post = Generator.generateRandomPost(1);
        postService.persist(post);
        Comment comment = Generator.generateRandomComment(1);
        commentService.persist(comment, post);
        SubComment subComment = Generator.generateRandomSubComment(1);
        commentService.persistSubComment(subComment, comment, post);

        commentService.delete(subComment, post);

        Assertions.assertEquals(0, comment.getSubComments().size());
    }
}
