package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private Integer authorId; // TODO probably change later

    @NotNull
    private LocalDateTime createdAt;
    // TODO images
}
