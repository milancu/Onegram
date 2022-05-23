package cz.nss.onegram.post.graphql.input.comment;

import lombok.Data;

@Data
public class DeleteSubcommentInput {
    private String postId;

    private String subcommentId;
}
