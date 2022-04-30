package cz.nss.onegram.post.graphql.input.like;

import lombok.Data;

@Data
public class DeleteLikeInput {
    private String postId;

    private String likeableId;
}
