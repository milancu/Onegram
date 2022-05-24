package cz.nss.onegram.user.security.rest;

import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{user_id}/following")
    public List<Integer> getFollowing(@PathVariable Integer user_id) {
        return userRepository.findById(user_id).get().getFollowing().stream().map(AbstractEntity::getId).toList();
    }
}
