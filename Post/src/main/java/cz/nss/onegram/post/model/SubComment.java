package cz.nss.onegram.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubComment {
    @NotNull
    private String content;
    @NotNull
    private Integer authorId;
}
