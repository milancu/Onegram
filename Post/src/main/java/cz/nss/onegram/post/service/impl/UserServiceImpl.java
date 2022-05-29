package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.graphql.input.comment.CreateCommentInput;
import cz.nss.onegram.post.graphql.input.comment.CreateSubcommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteCommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteSubcommentInput;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.repository.PostRepository;
import cz.nss.onegram.post.rest.interfaces.UserMicroserviceClient;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.CommentService;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final PostService postService;

    private final CommentService commentService;

    private final UserMicroserviceClient userMicroserviceClient;

    public UserDetailsImpl getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            return (UserDetailsImpl) auth.getPrincipal();
        }
        return null;
    }

    /**
     * @return boolean true if user created the post
     */
    public boolean userCreatedPost(String postId, UserDetailsImpl user){
        log.debug("Checking if user created a post.");
        Post post = postService.findById(postId);
        return post.getAuthorId().equals(user.getId());
    }

    @Override
    public boolean userCreatedComment(String postId, String commentId, UserDetailsImpl user) {
        log.debug("Checking if user created a comment.");
        Post post = postService.findById(postId);
        Comment comment = commentService.findById(commentId, post);
        return comment.getAuthorId().equals(user.getId());
    }

    @Override
    public boolean userCreatedSubcomment(String postId, String subCommentId, UserDetailsImpl user) {
        log.debug("Checking if user created a subcomment.");
        Post post = postService.findById(postId);
        SubComment subComment = commentService.findSubCommentById(subCommentId, post);
        return subComment.getAuthorId().equals(user.getId());
    }

    @Override
    public boolean userCreatedComment(DeleteCommentInput input, UserDetailsImpl user) {
        return userCreatedComment(input.getPostId(), input.getId(), user);
    }

    @Override
    public boolean userCreatedSubcomment(DeleteSubcommentInput input, UserDetailsImpl user) {
        return userCreatedSubcomment(input.getPostId(), input.getSubcommentId(), user);
    }

    /**
     * @param userId Id of the user, we are searching the users for.
     * @return Ids of the users, the given user follows.
     */
    @Override
    public List<Integer> getUserIdsUserFollows(Integer userId) {
        return userMicroserviceClient.fetchUsersUserFollows(userId);
    }
}
