package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

    @Override
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        UserDetails user = this.fetchUserFromUserMicroservice(jwt);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }

    private UserDetails fetchUserFromUserMicroservice(String jwt){
        HttpEntity<String> request = getRequest(jwt);
        UserDetailsImpl user
                = restTemplate.exchange(reqUrl, HttpMethod.GET, request, UserDetailsImpl.class).getBody();

        return user;
    }

    private HttpEntity<String> getRequest(String jwt){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt.split(" ")[1]); // TODO might cause problems
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
