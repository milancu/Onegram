package cz.nss.onegram.user.rest;

import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.exception.UserServiceException;
import cz.nss.onegram.user.model.AbstractEntity;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/{user_id}/following")
    public List<Integer> getFollowing(@PathVariable Integer user_id) {
        return userRepository.findById(user_id).get().getFollowing().stream().map(AbstractEntity::getId).toList();
    }

    @GetMapping("/user/{currUser_id}/{user_id}")
    public UserHelperDTO getUser(@PathVariable Integer currUser_id, @PathVariable Integer user_id) {
        User curr = userService.findById(currUser_id);
        User user = userService.findById(user_id);

        UserHelperDTO userHelperDTO = new UserHelperDTO(user);
        userHelperDTO.setFollowingByCurrentUser(curr.getFollowing().contains(user));

        return userHelperDTO;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public String broadcastGroupMessage(@Payload String message) {
        //Sending this message to all the subscribers
        //TODO filter asi
        return message;
    }
}
