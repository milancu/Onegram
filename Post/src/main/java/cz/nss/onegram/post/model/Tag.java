package cz.nss.onegram.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Tag {
    @NotNull
    private String content;
}
