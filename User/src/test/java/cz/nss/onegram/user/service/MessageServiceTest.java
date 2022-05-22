package cz.nss.onegram.user.service;

import config.TestConfiguration;
import graphql.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ContextConfiguration(classes = {TestConfiguration.class})
public class MessageServiceTest {

    @Test
    public void test(){
        Assert.assertNotNull("test");
    }
}
