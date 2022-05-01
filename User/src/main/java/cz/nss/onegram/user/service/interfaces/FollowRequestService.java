package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.User;

public interface FollowRequestService {

    public void sendFollowRequest(User user);
}
