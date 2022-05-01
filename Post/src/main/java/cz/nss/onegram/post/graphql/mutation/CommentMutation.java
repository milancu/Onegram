package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.comment.CreateCommentInput;
import cz.nss.onegram.post.graphql.input.comment.CreateSubcommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteCommentInput;
import cz.nss.onegram.post.graphql.input.comment.DeleteSubcommentInput;
import cz.nss.onegram.post.model.Comment;
import cz.nss.onegram.post.model.SubComment;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommentMutation implements GraphQLMutationResolver {
    private final InputMapper mapper;

    public Comment createComment(CreateCommentInput input){
        return null;
    }

    public SubComment createSubcomment(CreateSubcommentInput input){
        return null;
    }

    public Integer deleteComment(DeleteCommentInput input){
        return null;
    }

    public Integer deleteSubcomment(DeleteSubcommentInput input){
        return null;
    }
}
