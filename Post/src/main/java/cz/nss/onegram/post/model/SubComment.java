package cz.nss.onegram.post.model;

import cz.nss.onegram.post.model.interfaces.Likeable;
import cz.nss.onegram.post.service.interfaces.LikeService;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class SubComment implements Likeable {
    @NotNull
    private String id;

    @NotNull
    private String content;

    @NotNull
    private Integer authorId;

    private List<Like> likes;

    @Override
    public void accept(LikeService likeService, Like like, Post post) {
        likeService.delete(like, this, post);
    }
}
