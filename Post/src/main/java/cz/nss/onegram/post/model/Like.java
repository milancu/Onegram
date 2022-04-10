package cz.nss.onegram.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Like {
    @NotNull
    private Integer authorId; // TODO probably change later
}
