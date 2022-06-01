package cz.nss.onegram.post.graphql;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.impl.UserDetailsServiceImpl;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
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

    private final UserService userService;

    public Post getPost(String id) {
        log.info("Getting post with id: {}", id);

        return postService.findById(id);
    }

    public List<Post> getPosts(LocalDate from, LocalDate to){
        log.info("Getting posts from date {} to date {}.", from, to);

        return postService.findAll(from, to);
    }

    public List<Post> getUserPosts(Integer authorId) {
        log.info("Getting user's post with id: {}", authorId);

        return postService.findByAuthorId(authorId);
    }

    public List<Post> getUserPostsTime(Integer authorId, LocalDate from, LocalDate to) {
        log.info("Getting user's post with id: {}. Date range is <{}, {}>", authorId, from, to);

        return postService.findByAuthorId(authorId, from, to);
    }

    public List<Post> getFollowingsPosts(){
        log.info("Getting posts of the users, the currently logged in user follows");
        UserDetailsImpl currentUser = userService.getCurrentUser();
        List<Integer> userFollows = userService.getUserIdsUserFollows(currentUser.getId());

        return postService.findByAuthorIds(userFollows);
    }
}
