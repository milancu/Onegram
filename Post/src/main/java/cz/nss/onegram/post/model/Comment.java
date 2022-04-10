package cz.nss.onegram.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Comment {
    @NotNull
    private String content;
    @NotNull
    private Integer authorId;
    private List<SubComment> subComments;
}
