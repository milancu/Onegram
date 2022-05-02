package cz.nss.onegram.user.security;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.UserService;
import cz.nss.onegram.user.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    private final OAuth2AuthorizedClientService authorizedClientService;

    private String homeUrl = "http://localhost:1010/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken o2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User client = o2Token.getPrincipal();
        String email = client.getAttribute("email");
        User savedUser = userService.findByEmail(email);

        if (savedUser == null){
            userService.persist(client);
            log.info("Registrating new user: {}", email);
        }

        String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
                .queryParam("auth_token", JwtTokenUtil.generateToken(userService.findByEmail(email)))
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);

    }

}