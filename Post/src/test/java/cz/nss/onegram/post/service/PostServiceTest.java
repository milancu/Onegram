package cz.nss.onegram.post.service;


import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.service.impl.PostServiceImpl;
import cz.nss.onegram.post.service.interfaces.PostService;
import environment.Generator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PostServiceTest {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void removeData(){
        postRepository.deleteAll(postService.findAll());
    }

    @Test
    public void persist_persistRegularPost_postFound() {
        Post post = Generator.generateRandomPost(1);

        postService.persist(post);

        Assertions.assertNotNull(postService.findById(post.getId()));
    }

    @Test
    public void findByAuthorId_4postsCreated_2PostsFound() {
        Post post1 = Generator.generateRandomPost(1);
        Post post2 = Generator.generateRandomPost(1);
        Post post3 = Generator.generateRandomPost(2);
        Post post4 = Generator.generateRandomPost(5);
        postService.persist(post1);
        postService.persist(post2);
        postService.persist(post3);
        postService.persist(post4);

        List<Post> result = postService.findByAuthorId(1);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void findByAuthorIdWithDate_4postsCreated_2PostsFound() {
        Post post1 = Generator.generateRandomPost(1);
        post1.setCreatedAt(LocalDateTime.of(1990, 10, 10, 0, 0, 0));
        Post post2 = Generator.generateRandomPost(1);
        post2.setCreatedAt(LocalDateTime.of(2050, 10, 10, 0, 0, 0));
        Post post3 = Generator.generateRandomPost(1);
        post3.setCreatedAt(LocalDateTime.of(2022, 10, 10, 10,10, 10));
        Post post4 = Generator.generateRandomPost(1);
        post4.setCreatedAt(LocalDateTime.of(2022, 9, 9, 9,9, 9));
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);

        List<Post> result = postService.findByAuthorId(1, LocalDate.of(2022, 9, 9), LocalDate.of(2022, 10, 10));

        Assertions.assertEquals(2, result.size());
    }
}
