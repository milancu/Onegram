package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.repository.PostRepository;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemInitializerImpl implements SystemInitializer {
    private final PostService postService;

    private final PostRepository postRepository;

    private final Environment environment;

    @Override
    @PostConstruct
    public void initSystem() {;
        postRepository.deleteAll(postService.findAll());
        log.info("All posts deleted!");

        if (Arrays.asList(environment.getActiveProfiles()).contains("testprofile")) {
            log.warn("System initializer stopped, testprofile active, no data will be generated.");
            return;
        }

        createPost("Přeji krásně pondělí!", 1);
        createPost("Přeji krásnou něděli!", 5);
    }

    private void createPost(String description, Integer authorId){
        Post post = Post.builder()
                .authorId(authorId)
                .comments(List.of())
                .createdAtDate(LocalDate.now())
                .createdAtTime(LocalTime.now())
                .description(description)
                .likes(List.of())
                .tags(List.of())
                .build();

        postRepository.save(post);
        log.info("Generated post: {}", post);
    }
}
