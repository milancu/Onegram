package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.post.CreatePostInput;
import cz.nss.onegram.post.graphql.input.post.DeletePostInput;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final InputMapper mapper;

    private final PostService postService;

    public Post createPost(CreatePostInput input){
        Post post = mapper.convertToEntity(input);
        log.warn("Changing author id because of nonexsiting security");
        post.setAuthorId(100); // TODO remove
        postService.persist(post);
        log.info("Post created: {}", post);
        return post;
    }

    public Integer deletePost(DeletePostInput input){
        Post post = postService.findById(input.getId());
        if (post != null){
            log.info("Deleting post: {}", post);
            postService.delete(post);
            log.info("Post id {} deleted", input.getId());
            return 1;
        }

        else {
            return 0;
        }
    }
}
