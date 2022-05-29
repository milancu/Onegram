package cz.nss.onegram.user.service;

import cz.nss.onegram.user.dao.FollowRequestRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.exception.UserServiceException;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.security.services.UserDetailsServiceImpl;
import cz.nss.onegram.user.service.impl.UserServiceImpl;
import enviroment.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

    private final Generator generator = new Generator();

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRequestRepository followRequestRepository;

    public void setCurrentUserToContext(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @BeforeEach
    public void removeData() {
        followRequestRepository.deleteAll(followRequestRepository.findAll());
        userRepository.deleteAll(userRepository.findAll());
    }

    @Test
    public void persists_followPublicAccount() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User bureson2 = generator.generateBureson2();
        userService.persist(bureson2);

        //ACT
        userService.followUser(bureson2.getId());

        //ASSERT
        int followers = userRepository.findByEmail(bureson2.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(bureson2.getId()).size();
        Assertions.assertEquals(1, followers);
        Assertions.assertEquals(1, following);
        Assertions.assertEquals(0, sentRequest);
        Assertions.assertEquals(0, receivedRequest);

    }

    @Test
    public void persists_twoFollowPublicAccount_onlyOnePersisted() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User bureson2 = generator.generateBureson2();
        userService.persist(bureson2);

        //ACT
        userService.followUser(bureson2.getId());
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.followUser(bureson2.getId());
            ;
        });


        //ASSERT
        String expectedMessage = "cz.nss.onegram.user.exception.UserServiceException: User is already being followed";
        int followers = userRepository.findByEmail(bureson2.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(bureson2.getId()).size();
        Assertions.assertTrue(expectedMessage.contains(exception.getMessage()));
        Assertions.assertEquals(1, followers);
        Assertions.assertEquals(1, following);
        Assertions.assertEquals(0, sentRequest);
        Assertions.assertEquals(0, receivedRequest);

    }

    @Test
    public void persists_followPrivateAccount() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User belkapre = generator.generateBelkapre();
        userService.persist(belkapre);

        //ACT
        userService.followUser(belkapre.getId());

        //ASSERT
        int followers = userRepository.findByEmail(belkapre.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(belkapre.getId()).size();
        Assertions.assertEquals(0, followers);
        Assertions.assertEquals(0, following);
        Assertions.assertEquals(1, sentRequest);
        Assertions.assertEquals(1, receivedRequest);

    }

    @Test
    public void persists_twoFollowRequestFromSameUser_onlyOneRequestPersisted() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User belkapre = generator.generateBelkapre();
        userService.persist(belkapre);

        //ACT
        userService.followUser(belkapre.getId());
        userService.followUser(belkapre.getId());

        //ASSERT
        int followers = userRepository.findByEmail(belkapre.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(belkapre.getId()).size();
        Assertions.assertEquals(0, followers);
        Assertions.assertEquals(0, following);
        Assertions.assertEquals(1, sentRequest);
        Assertions.assertEquals(1, receivedRequest);

    }

    @Test
    public void remove_following() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User bureson2 = generator.generateBureson2();
        userService.persist(bureson2);
        userService.followUser(bureson2.getId());

        //ACT
        userService.unFollowUser(bureson2.getId());

        //ASSERT
        int followers = userRepository.findByEmail(bureson2.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        Assertions.assertEquals(0, followers);
        Assertions.assertEquals(0, following);
    }

    @Test
    public void remove_follower() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User bureson2 = generator.generateBureson2();
        userService.persist(bureson2);

        userService.followUser(bureson2.getId());
        setCurrentUserToContext(bureson2);

        //ACT
        userService.removeFollower(cuphuon3.getId());

        //ASSERT
        int followers = userRepository.findByEmail(bureson2.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        Assertions.assertEquals(0, followers);
        Assertions.assertEquals(0, following);
    }

    @Test
    public void accept_followRequest() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        User belkapre = generator.generateBelkapre();
        userService.persist(belkapre);

        userService.followUser(belkapre.getId());
        setCurrentUserToContext(belkapre);

        //ACT
        userService.acceptRequest(followRequestRepository.getRequestToUser(cuphuon3.getId(), belkapre.getId()).getId());

        //ASSERT
        int followers = userRepository.findByEmail(belkapre.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(belkapre.getId()).size();
        Assertions.assertEquals(1, followers);
        Assertions.assertEquals(1, following);
        Assertions.assertEquals(0, sentRequest);
        Assertions.assertEquals(0, receivedRequest);
    }

    @Test
    public void reject_followRequest() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);


        User belkapre = generator.generateBelkapre();
        userService.persist(belkapre);

        userService.followUser(belkapre.getId());
        setCurrentUserToContext(belkapre);


        //ACT
        userService.rejectRequest(followRequestRepository.getRequestToUser(cuphuon3.getId(), belkapre.getId()).getId());

        //ASSERT
        int followers = userRepository.findByEmail(belkapre.getEmail()).getFollower().size();
        int following = userRepository.findByEmail(cuphuon3.getEmail()).getFollowing().size();
        int sentRequest = followRequestRepository.getAllSentRequestFromUser(cuphuon3.getId()).size();
        int receivedRequest = followRequestRepository.getAllReceivedRequestOfUser(belkapre.getId()).size();
        Assertions.assertEquals(0, followers);
        Assertions.assertEquals(0, following);
        Assertions.assertEquals(0, sentRequest);
        Assertions.assertEquals(0, receivedRequest);
    }

    @Test
    public void makeProfilePublic() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        //ACT
        userService.makeProfilePublic();

        //ASSERT
        cuphuon3 = userRepository.findByEmail(cuphuon3.getEmail());
        Assertions.assertTrue(cuphuon3.isPublic());
    }

    @Test
    public void makeProfilePrivate() {

        //ARRANGE
        User bureson2 = generator.generateBureson2();
        userService.persist(bureson2);
        setCurrentUserToContext(bureson2);

        //ACT
        userService.makeProfilePrivate();

        //ASSERT
        bureson2 = userRepository.findByEmail(bureson2.getEmail());
        Assertions.assertFalse(bureson2.isPublic());
    }

    @Test
    public void persists_bio() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        userService.persist(cuphuon3);
        setCurrentUserToContext(cuphuon3);

        //ACT
        String newBio = "Stale jsem nejhezci";
        userService.editBio(newBio);

        //ASSERT
        cuphuon3 = userRepository.findByEmail(cuphuon3.getEmail());
        Assertions.assertEquals(newBio, cuphuon3.getBio());
    }
}
