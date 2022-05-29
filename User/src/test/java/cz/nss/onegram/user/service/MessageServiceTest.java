package cz.nss.onegram.user.service;

import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.security.services.UserDetailsServiceImpl;
import cz.nss.onegram.user.service.impl.MessageServiceImpl;
import cz.nss.onegram.user.service.impl.UserServiceImpl;
import enviroment.Generator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MessageServiceTest {

    private final Generator generator = new Generator();

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public void setCurrentUserToContext(User user){
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @BeforeEach
    @AfterEach
    public void removeData(){
        messageRepository.deleteAll(messageRepository.findAll());
        userRepository.deleteAll(userRepository.findAll());
    }

    @Test
    public void persists_message() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        User belkapre = generator.generateBelkapre();
        userService.persist(cuphuon3);
        userService.persist(belkapre);
        Message message = generator.generateMessage(cuphuon3, belkapre, "Preji krasnou nedeli");

        //ACT
        messageRepository.save(message);

        //ASSERT
        setCurrentUserToContext(cuphuon3);
        int conversation1 = messageService.getAllMessageWithUser(belkapre.getId()).size();

        setCurrentUserToContext(belkapre);
        int conversation2 = messageService.getAllMessageWithUser(cuphuon3.getId()).size();

        Assertions.assertEquals(1, conversation1);
        Assertions.assertEquals(1, conversation2);

    }

    @Test
    public void delete_message() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        User belkapre = generator.generateBelkapre();
        userService.persist(cuphuon3);
        userService.persist(belkapre);
        setCurrentUserToContext(cuphuon3);
        Message message = generator.generateMessage(cuphuon3, belkapre, "Preji krasnou nedeli");
        messageRepository.save(message);

        //ACT
        messageService.removeMessage(message.getId());

        //ASSERT
        setCurrentUserToContext(belkapre);
        int conversation1 = messageService.getAllMessageWithUser(belkapre.getId()).size();

        setCurrentUserToContext(cuphuon3);
        int conversation2 = messageService.getAllMessageWithUser(cuphuon3.getId()).size();

        Assertions.assertEquals(0, conversation1);
        Assertions.assertEquals(0, conversation2);
    }

    @Test
    public void persists_readMessage() {

        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        User belkapre = generator.generateBelkapre();
        userService.persist(cuphuon3);
        userService.persist(belkapre);
        Message message = generator.generateMessage(cuphuon3, belkapre, "Preji krasnou nedeli");
        messageRepository.save(message);

        //ACT
        setCurrentUserToContext(belkapre);
        messageService.makeMessageRead(message.getId());

        //ASSERT
        Assertions.assertTrue(message.isHasRead());

    }

    @Test
    public void persists_unreadMessage() {
        //ARRANGE
        User cuphuon3 = generator.generateCuphuon3();
        User belkapre = generator.generateBelkapre();
        userService.persist(cuphuon3);
        userService.persist(belkapre);
        Message message = generator.generateMessage(cuphuon3, belkapre, "Preji krasnou nedeli");
        messageRepository.save(message);

        //ACT
        setCurrentUserToContext(belkapre);
        messageService.makeMessageRead(message.getId());
        messageService.makeMessageUnread(message.getId());

        //ASSERT
        Assertions.assertTrue(message.isHasRead());
    }

}
