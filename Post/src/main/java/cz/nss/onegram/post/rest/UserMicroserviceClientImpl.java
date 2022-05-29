package cz.nss.onegram.post.rest;

import cz.nss.onegram.post.rest.interfaces.UserMicroserviceClient;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.impl.CacheServiceImpl;
import cz.nss.onegram.post.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMicroserviceClientImpl implements UserMicroserviceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "http://localhost:1010/rest"; // TODO remove hardcoded value, might be problem when deployed

    private final String AUTH_URL = "http://localhost:1010/auth/current";

    @Override
    public List<Integer> fetchUsersUserFollows(Integer userId) {
        String reqUrl = BASE_URL + "/" + userId + "/following";
        HttpEntity<String> httpRequest = createRequest();

        return restTemplate.exchange(reqUrl, HttpMethod.GET, httpRequest, List.class).getBody();
    }

    private HttpEntity<String> createRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UserDetailsImpl currentUser = UserDetailsServiceImpl.getLoadedUser();
        if (currentUser != null){
            headers.setBearerAuth(currentUser.getJwt());
        }

        return new HttpEntity<>(headers);
    }

    @Cacheable("USER")
    public UserDetails fetchUserFromUserMicroservice(String jwt){
        HttpEntity<String> request = createRequest(jwt);
        UserDetailsImpl user
                = restTemplate.exchange(AUTH_URL, HttpMethod.GET, request, UserDetailsImpl.class).getBody();
        return user;
    }

    private HttpEntity<String> createRequest(String jwt){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt.split(" ")[1]); // TODO might cause problems
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
