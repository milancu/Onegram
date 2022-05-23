package cz.nss.onegram.post.rest;

import cz.nss.onegram.post.rest.interfaces.UserMicroserviceClient;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.impl.UserDetailsServiceImpl;
import cz.nss.onegram.post.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMicroserviceClientImpl implements UserMicroserviceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "http://localhost:1010/rest"; // TODO remove hardcoded value, might be problem when deployed

    @Override
    public List<Integer> fetchUsersUserFollows(Integer userId) {
        String reqUrl = baseUrl + "/" + userId + "/following";
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
}
