package cz.nss.onegram.user.security;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.security.jwt.JwtUtils;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired(required = false) // Required = false because of tests
    private AuthenticationManager authenticationManager;
    @Autowired(required = false) // Required = false because of tests
    private JwtUtils jwtUtils;


    private String homeUrl = "http://localhost:1010/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken o2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User client = o2Token.getPrincipal();
        String email = client.getAttribute("email");
        User savedUser = userService.findByEmail(email);

        if (savedUser == null) {
            userService.persist(client);
            log.info("Registrating new user: {}", email);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(email);
        String redirectionUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect/")
                .queryParam("Authorization", jwt)
                .build().toUriString();

        response.setHeader("Authorization", jwt);
        log.info("Created new jwtToken: {}", jwt);
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
    }

}