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

    public User followUser(FollowUserInput input) {
        return userService.followUser(input.getUserId());
    }

    public User unFollowUser(UnFollowUserInput input) {
        userService.unFollowUser(input.getUserId());
        return null;
    }

    public int removeFollower(UnFollowUserInput input){ //TODO return
        userService.removeFollower(input.getUserId());
        return 1;
    }

    public Integer acceptRequest(AcceptRequestInput input) {
        userService.acceptRequest(input.getRequestId());
        return 1;
    }

    public Integer rejectRequest(RejectRequestInput input) {
        userService.rejectRequest(input.getRequestId());
        return 1;
    }

    public User makeProfilePublic(){
        userService.makeProfilePublic();
        User user = userService.getCurrentUser();
        return user;
    }

    public User makeProfilePrivate(){
        userService.makeProfilePrivate();
        User user = userService.getCurrentUser();
        return user;
    }

    public User editBio(EditBioInput input){
        userService.editBio(input.getBio());
        return userService.getCurrentUser(); //TODO return
    }

    public User setProfilePhoto(DataFetchingEnvironment environment) throws IOException {
        UploadUtil.validateUploadedImages(environment);
        List<InputStream> files = UploadUtil.extractFiles(environment);
        if (files.size() != 1){
            throw new RuntimeException("Exactly one image must be passed."); // TODO replace with application specific exception for the API handler
        }
        User user = userService.getCurrentUser();
        userService.addPhoto(user, files.get(0));
        return user;
    }
}
