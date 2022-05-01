package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.model.UserDetails;
import cz.nss.onegram.post.service.interfaces.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private HttpServletRequest request;

    private RestTemplate restTemplate = new RestTemplate();

    private String reqUrl = "http://seznam.cz"; // TODO remove hardcoded value

    @Override
    public String getCurrentUser() { // Return UserDetails
        ResponseEntity<String> response
                = restTemplate.exchange(reqUrl, HttpMethod.GET, extractToken(), String.class);
        String body = response.getBody();
        return body;
    }

    private HttpEntity<String> extractToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(request.getHeader("Authorization"));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
