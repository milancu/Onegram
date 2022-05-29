package cz.nss.onegram.post.service.interfaces;

import cz.nss.onegram.post.graphql.input.comment.CreateCommentInput;
import cz.nss.onegram.post.graphql.input.comment.CreateSubcommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteCommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteSubcommentInput;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    public UserDetailsImpl getCurrentUser();

    public List<Integer> getUserIdsUserFollows(Integer userId);

    public boolean userCreatedPost(String postId, UserDetailsImpl user);

    public boolean userCreatedComment(String postId, String commentId, UserDetailsImpl user);

    public boolean userCreatedSubcomment(String postId, String subCommentId, UserDetailsImpl user);

    public boolean userCreatedComment(DeleteCommentInput input, UserDetailsImpl user);

    public boolean userCreatedSubcomment(DeleteSubcommentInput input, UserDetailsImpl user);
}
