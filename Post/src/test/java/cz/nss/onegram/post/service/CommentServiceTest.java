package cz.nss.onegram.post.service;

import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
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
}
