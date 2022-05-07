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
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostResolver implements GraphQLQueryResolver {
    private final PostService postService;

    public List<Post> getPosts() {
        log.info("Getting all posts.");

        return postService.findAll();
    }
}
