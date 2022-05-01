package cz.nss.onegram.post.graphql.input.comment;

import lombok.Data;

@Data
public class CreateSubcommentInput {
    private String postId;

    private String mainCommentId;

    private String content;
}
