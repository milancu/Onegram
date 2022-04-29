package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@Builder
public class Post implements Likeable {
    @Id
    private Integer id;

    @NotNull
    private String description;

    private List<Tag> tags;

    private List<Like> likes;

    private List<Comment> comments;

    @NotNull
    private Integer authorId; // TODO probably change later

    @NotNull
    private LocalDateTime createdAt;
    // TODO images
}
