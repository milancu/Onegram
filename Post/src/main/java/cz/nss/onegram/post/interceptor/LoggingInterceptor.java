package cz.nss.onegram.post.interceptor;

import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDetailsImpl currentUser = userService.getCurrentUser();
        if (!Objects.isNull(currentUser)){
            log.info("Request-uri: {}, By user: {}", request.getRequestURI(), currentUser.getUsername());
        }

        else{
            log.info("Request-uri: {}, By user: NOT_AUTHENTICATED", request.getRequestURI());
        }

        return true;
    }
}
