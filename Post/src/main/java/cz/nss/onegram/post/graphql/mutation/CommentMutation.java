package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.comment.CreateCommentInput;
import cz.nss.onegram.post.graphql.input.comment.CreateSubcommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteCommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteSubcommentInput;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.CommentService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommentMutation implements GraphQLMutationResolver {
    private final InputMapper mapper;

    private final CommentService commentService;

    private final PostService postService;

    private final UserService userService;

    public Comment createComment(CreateCommentInput input){
        Post post = postService.findById(input.getPostId());
        UserDetailsImpl user = userService.getCurrentUser();
        Comment comment = mapper.convertToEntity(input, user.getId());
        commentService.persist(comment, post);
        log.info("Comment created: {} on post_id: {}", comment, post.getId());

        return comment;
    }

    public SubComment createSubcomment(CreateSubcommentInput input){
        Post post = postService.findById(input.getPostId());
        Comment mainComment = commentService.findById(input.getMainCommentId(), post);
        UserDetailsImpl user = userService.getCurrentUser();
        SubComment subComment = mapper.convertToEntity(input, user.getId());
        commentService.persistSubComment(subComment, mainComment, post);
        log.info("Subcomment created: {} on post_id: {}", subComment, post.getId());

        return subComment;
    }

    @PreAuthorize("@userServiceImpl.userCreatedComment(#input, @userServiceImpl.getCurrentUser())" +
            " || @userServiceImpl.userCreatedPost(#input.postId, @userServiceImpl.getCurrentUser())")
    public Integer deleteComment(DeleteCommentInput input){
        Post post = postService.findById(input.getPostId());
        Comment comment = commentService.findById(input.getId(), post);
        commentService.delete(comment, post);
        log.info("Comment deleted: " + input);
        return 1;
    }

    @PreAuthorize("@userServiceImpl.userCreatedSubcomment(#input, @userServiceImpl.getCurrentUser())" +
            " || @userServiceImpl.userCreatedPost(#input.postId, @userServiceImpl.getCurrentUser())")
    public Integer deleteSubcomment(DeleteSubcommentInput input){
        Post post = postService.findById(input.getPostId());
        SubComment subComment = commentService.findSubCommentById(input.getSubcommentId(), post);
        commentService.delete(subComment, post);
        log.info("Subcomment deleted: " + input);
        return 1;
    }
}
