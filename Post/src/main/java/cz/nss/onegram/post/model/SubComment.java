package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SubComment implements Likeable {
    @NotNull
    private String content;

    @NotNull
    private Integer authorId;

    private List<Like> likes;
}
