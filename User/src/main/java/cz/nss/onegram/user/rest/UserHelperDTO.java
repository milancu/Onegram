package cz.nss.onegram.user.rest;

import cz.nss.onegram.user.model.User;
import lombok.Data;

@Data
public class UserHelperDTO {

    private Integer id;
    private String username;
    private String email;
    private boolean isPublic;
    private boolean isFollowingByCurrentUser;

    public UserHelperDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isPublic = user.isPublic();
    }
}
