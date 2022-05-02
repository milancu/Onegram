package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.model.FollowRequest;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.model.enums.FollowState;
import cz.nss.onegram.user.service.interfaces.FollowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FollowRequestServiceImpl implements FollowRequestService {
    private final FollowRequestRepository followRequestRepository;
    @Override
    public void sendFollowRequest(User fromUser, User userToFollow) {
        FollowRequest followRequest = new FollowRequest();
        followRequest.setFromUser(fromUser);
        followRequest.setToUser(userToFollow);
        followRequest.setDate(LocalDate.now());
        followRequest.setFollowState(FollowState.PENDING);
    }
}
