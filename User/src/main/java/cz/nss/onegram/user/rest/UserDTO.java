package cz.nss.onegram.user.rest;

import cz.nss.onegram.user.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
