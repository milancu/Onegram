package cz.nss.onegram.post.rest.interfaces;


import java.util.List;

public interface UserMicroserviceClient {

    public List<Integer> fetchUsersUserFollows(Integer userId);
}
