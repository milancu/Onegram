package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SubComment implements Likeable {
    @NotNull
    private String id;

    @NotNull
    private String content;

    @NotNull
    private Integer authorId;

    @NotNull
    private LocalDate createdAtDate;

    @NotNull
    private LocalTime createdAtTime;

    private List<Like> likes;
}
