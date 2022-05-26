package cz.nss.onegram.user.rest;

import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/current")
    public UserDTO getCurrentUser(){
        UserDTO userDTO = new UserDTO(userService.getCurrentUser());

        return userDTO;
    }
}
