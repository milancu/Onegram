package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.post.CreatePostInput;
import cz.nss.onegram.post.graphql.input.post.DeletePostInput;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.FileService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import cz.nss.onegram.post.util.InputMapper;
import cz.nss.onegram.post.util.UploadUtil;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final InputMapper mapper;

    private final PostService postService;

    private final UserService userService;

    private final FileService fileService;

    public Post createPost(CreatePostInput input, DataFetchingEnvironment environment) throws IOException {
        UploadUtil.validateUploadedImages(environment);
        List<InputStream> files = UploadUtil.extractFiles(environment);
        List<String> paths = fileService.saveAsPngFiles(files);
        Post post = mapper.convertToEntity(input, paths);
        UserDetailsImpl user = userService.getCurrentUser();
        post.setAuthorId(user.getId());
        postService.persist(post);
        log.info("Post created: {} By user: {}", post, user);
        return post;
    }

    @PreAuthorize("@userServiceImpl.userCreatedPost(#input.id, @userServiceImpl.getCurrentUser())")
    public Integer deletePost(DeletePostInput input){
        Post post = postService.findById(input.getId());
        log.info("Deleting post: {}", post);
        postService.delete(post);
        log.info("Post id {} deleted", input.getId());
        return 1;
    }
}
