package cz.nss.onegram.user.graphql.input.user;

import lombok.Data;

@Data
public class EditProfileInput {
    private String username;
    private String bio;
    private String link;
}
