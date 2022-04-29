package cz.nss.onegram.post.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Like {
    @NotNull
    private Integer authorId; // TODO probably change later
}
