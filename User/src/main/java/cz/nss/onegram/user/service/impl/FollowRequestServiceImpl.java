package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.FollowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowRequestServiceImpl implements FollowRequestService {
    @Override
    public void sendFollowRequest(User user) {

    }
}
