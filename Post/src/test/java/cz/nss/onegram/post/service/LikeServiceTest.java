package cz.nss.onegram.post.service;

import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
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
public class LikeServiceTest {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private LikeServiceImpl likeService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void removeData(){
        postRepository.deleteAll(postService.findAll());
    }

    @Test
    public void persists_twoLikesFromSameUser_OnlyOneLikePersisted(){
        Post post = Generator.generateRandomPost(2);
        Like like1 = Like.builder().authorId(5).build();
        Like like2 = Like.builder().authorId(5).build();

        likeService.persist(like1, post, post);
        likeService.persist(like2, post, post);

        Integer result = postService.findById(post.getId()).getLikes().size();
        Assertions.assertEquals(1, result);
    }
}
