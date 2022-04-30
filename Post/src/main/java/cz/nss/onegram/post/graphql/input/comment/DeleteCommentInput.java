package cz.nss.onegram.post.graphql.input.comment;

import lombok.Data;

@Data
public class DeleteCommentInput {
    private String postId;

    private String id;
}
