package cz.nss.onegram.post.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class Tag {
    @NotNull
    private String content;

    private List<Like> likes;
}
