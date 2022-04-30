package cz.nss.onegram.post.graphql.input.comment;

import lombok.Data;

@Data
public class CreateCommentInput {
    private String postId;

    private String content;
}
