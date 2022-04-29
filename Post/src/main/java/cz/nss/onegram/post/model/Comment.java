package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class Comment implements Likeable {
    @NotNull
    private String objectId;

    @NotNull
    private String content;

    @NotNull
    private Integer authorId;

    private List<SubComment> subComments;

    private List<Like> likes;
}
