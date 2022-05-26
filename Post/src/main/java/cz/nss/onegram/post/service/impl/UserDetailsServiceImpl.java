package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.rest.interfaces.UserMicroserviceClient;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String reqUrl = "http://localhost:1010/auth/current"; // TODO remove hardcoded value, might be problem because of protocol when deployed

    private final UserMicroserviceClient userMicroserviceClient;

    @Override
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        UserDetails user = userMicroserviceClient.fetchUserFromUserMicroservice(jwt);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        ((UserDetailsImpl) user).setJwt(jwt.split(" ")[1]); // TODO might cause problems
        return user;
    }

    public static UserDetailsImpl getLoadedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            return (UserDetailsImpl) auth.getPrincipal();
        }
        return null;
    }
}
