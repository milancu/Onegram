package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Likeable {
    @NotNull
    private String id;

    @NotNull
    private String content;

    @NotNull
    private Integer authorId;

    @NotNull
    private List<SubComment> subComments;

    @NotNull
    private List<Like> likes;

    public List<Likeable> getLikeables(){
        List<Likeable> likeables = new ArrayList<>(subComments);
        return likeables;
    }
}
