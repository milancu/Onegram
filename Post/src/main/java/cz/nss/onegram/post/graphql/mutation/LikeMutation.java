package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.like.CreateLikeInput;
import cz.nss.onegram.post.graphql.input.like.DeleteLikeInput;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LikeMutation implements GraphQLMutationResolver {
    private final InputMapper mapper;

    private final PostService postService;

    private final LikeService likeService;

    public Like createLike(CreateLikeInput input){
        return null;
    }

    public Integer deleteLike(DeleteLikeInput input){
        Post post = postService.findById(input.getPostId());
        boolean deleted = false;
        if (post != null){
            Likeable likeable = likeService.findById(input.getLikeableId(), post);
            if (likeable != null) {
                throw new UnsupportedOperationException("Musim vedet kdo to posila");
            }
        }
        return 0;
    }
}
