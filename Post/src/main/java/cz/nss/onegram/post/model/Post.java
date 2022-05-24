package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post implements Likeable {
    @Id
    private String id;

    @NotNull
    private String description;

    @NotNull
    private List<Tag> tags;

    @NotNull
    private List<Like> likes;

    @NotNull
    private List<Comment> comments;

    @NotNull
    private Integer authorId;

    @NotNull
    private LocalDate createdAtDate;

    @NotNull
    private LocalTime createdAtTime;

    @NotNull
    private List<String> imagePaths;

    public List<Likeable> getLikeables() {
        ArrayList<Likeable> likeables = new ArrayList<>();
        likeables.add(this);
        likeables.addAll(comments);
        comments.forEach((c) -> likeables.addAll(c.getLikeables()));

        return likeables;
    }
}
