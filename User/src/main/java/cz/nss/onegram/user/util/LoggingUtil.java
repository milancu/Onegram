package cz.nss.onegram.user.util;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingUtil {

    private final UserService userService;

    public void logRequest(HttpServletRequest request){
        User currentUser = userService.getCurrentUser();
        if (!Objects.isNull(currentUser)){
            log.info("{} Request-uri: {}, By user: {}",
                    request.getMethod(), request.getRequestURI(), currentUser.getUsername());
        }

        else{
            log.info("{} Request-uri: {}, By user: NOT_AUTHENTICATED",
                    request.getMethod(), request.getRequestURI());
        }
    }
}
