package cz.nss.onegram.user.graphql.mutation;

import cz.nss.onegram.user.graphql.input.user.*;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.FileService;
import cz.nss.onegram.user.service.interfaces.UserService;
import cz.nss.onegram.user.util.UploadUtil;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    private final FileService fileService;

    public Integer followUser(FollowUserInput input) {
        userService.followUser(input.getUserId());
        return 1;
    }

    public Integer unFollowUser(UnFollowUserInput input) {
        userService.unFollowUser(input.getUserId());
        return 1;
    }

    public Integer removeFollower(UnFollowUserInput input) {
        userService.removeFollower(input.getUserId());
        return 1;
    }

    @PreAuthorize("@userServiceImpl.hasReceivedRequest(#input.requestId)")
    public Integer acceptRequest(AcceptRequestInput input) {
        userService.acceptRequest(input.getRequestId());
        return 1;
    }

    @PreAuthorize("@userServiceImpl.hasReceivedRequest(#input.requestId)")
    public Integer rejectRequest(RejectRequestInput input) {
        userService.rejectRequest(input.getRequestId());
        return 1;
    }

    public Integer makeProfilePublic() {
        userService.makeProfilePublic();
        User user = userService.getCurrentUser();
        return 1;
    }

    public Integer makeProfilePrivate() {
        userService.makeProfilePrivate();
        User user = userService.getCurrentUser();
        return 1;
    }

    public Integer editBio(EditBioInput input) {
        userService.editBio(input.getBio());
        userService.getCurrentUser();
        return 1;
    }

    public Integer editProfile(EditProfileInput input) {
        userService.editBio(input.getBio());
        userService.editUsername(input.getUsername());
        userService.editLink(input.getLink());
        return 1;
    }

    public Integer setProfilePhoto(DataFetchingEnvironment environment) throws IOException {
        UploadUtil.validateUploadedImages(environment);
        List<InputStream> files = UploadUtil.extractFiles(environment);
        if (files.size() != 1) {
            throw new RuntimeException("Exactly one image must be passed."); // TODO replace with application specific exception for the API handler
        }
        User user = userService.getCurrentUser();
        userService.addPhoto(files.get(0));
        return 1;
    }

    public Integer editLink(EditLinkInput input) {
        userService.editLink(input.getLink());
        return 1;
    }

    public Integer editUsername(EditUsernameInput input) {
        userService.editUsername(input.getUsername());
        return 1;
    }
}
