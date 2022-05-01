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
import java.time.LocalDateTime;
import java.util.Arrays;
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

        Post post = Post.builder()
                .authorId(1)
                .comments(List.of())
                .createdAt(LocalDateTime.now())
                .description("Přeji krásné pondělí!")
                .likes(List.of())
                .tags(List.of())
                .build();

        postRepository.save(post);

        log.info("Generated post: {}", post);
    }
}
