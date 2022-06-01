package cz.nss.onegram.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(UserApplication.class)
                .properties("spring.config.location=classpath:application.properties,classpath:application.properties");
        app.run(args);
    }
}
