package cz.nss.onegram.user.security.rest;

import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.AbstractEntity;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {

    private final UserRepository userRepository;

    @GetMapping("/{user_id}/following")
    public List<Integer> getFollowing(@PathVariable Integer user_id) {
        return userRepository.findById(user_id).get().getFollowing().stream().map(AbstractEntity::getId).toList();
    }
}
