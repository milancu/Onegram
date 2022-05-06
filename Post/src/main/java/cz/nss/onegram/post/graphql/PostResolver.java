package cz.nss.onegram.post.graphql;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.service.impl.UserDetailsServiceImpl;
import cz.nss.onegram.post.service.interfaces.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostResolver implements GraphQLQueryResolver {
    private final PostService postService;

    public Post getPost(String id) {
        log.info("Getting post with id: {}", id);

        return postService.findById(id);
    }

    public List<Post> getUserPosts(Integer authorId) {
        log.info("Getting user's post with id: {}", authorId);

        return postService.findByAuthorId(authorId);
    }

    public List<Post> getUserPostsTime(Integer authorId, LocalDate from, LocalDate to) {
        log.info("Getting user's post with id: {}. Date range is <{}, {}>", authorId, from, to);

        return postService.findByAuthorId(authorId, from, to);
    }
}
