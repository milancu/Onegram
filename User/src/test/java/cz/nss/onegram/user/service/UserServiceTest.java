package cz.nss.onegram.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

//    private final Generator generator = new Generator();

//    @Autowired
//    private UserServiceImpl userService;
//
//    private User cuphuon3 = generator.generateCuphuon3();
//    private User belkapre = generator.generateBelkapre();
//    private User bureson2 = generator.generateBureson2();
//    private User pivonja1 = generator.generatePinvonja1();
//    private User naplava = generator.generateNaplava();
//    private User koci = generator.generateKoci();
//    private User sebekji1 = generator.generateSebek();
//    private User nagyoing = generator.generateNagyova();


    @Test
    public void test() {
        Assertions.assertEquals(0, 0);
    }
}
