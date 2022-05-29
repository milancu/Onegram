package cz.nss.onegram.post.model;

import com.mongodb.lang.Nullable;
import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @NotNull
    private LocalDate createdAtDate;

    @NotNull
    private LocalTime createdAtTime;

    public List<Likeable> getLikeables(){
        List<Likeable> likeables = new ArrayList<>(subComments);
        return likeables;
    }
}
