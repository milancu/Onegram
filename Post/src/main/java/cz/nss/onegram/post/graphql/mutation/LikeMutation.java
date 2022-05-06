package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.like.CreateLikeInput;
import cz.nss.onegram.post.graphql.input.like.DeleteLikeInput;
import cz.nss.onegram.post.model.Like;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
@RequiredArgsConstructor
public class LikeMutation implements GraphQLMutationResolver {
    private final InputMapper mapper;

    private final PostService postService;

    private final LikeService likeService;

    private final UserService userService;

    public Like createLike(CreateLikeInput input){
        Post post = postService.findById(input.getPostId());
        Likeable likeable = likeService.findLikeableById(input.getLikeableId(), post);
        Like like = mapper.convertToEntity(input, userService.getCurrentUser().getId());
        likeService.persist(like, likeable, post);
        log.info("Like created: " + input);
        return like;
    }

    public Integer deleteLike(DeleteLikeInput input){
        Post post = postService.findById(input.getPostId());
        Likeable likeable = likeService.findLikeableById(input.getLikeableId(), post);
        Like like = likeService.findLikeByUser(likeable, userService.getCurrentUser());
        likeService.delete(like, likeable, post);
        log.info("Like deleted: " + input);
        return 1;
    }
}
