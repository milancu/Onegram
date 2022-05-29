package cz.nss.onegram.post.rest.interfaces;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserMicroserviceClient {

    public List<Integer> fetchUsersUserFollows(Integer userId);

    public UserDetails fetchUserFromUserMicroservice(String jwt);
}
