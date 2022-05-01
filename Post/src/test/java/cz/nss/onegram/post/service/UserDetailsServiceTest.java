package cz.nss.onegram.post.service;

import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.service.interfaces.UserDetailsService;
import environment.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserDetailsServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void persist_persistRegularPost_postFound() {
        String result = userDetailsService.getCurrentUser();
        System.out.println(result);
    }

}
