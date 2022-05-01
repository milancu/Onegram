package cz.nss.onegram.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/post/graphql")
                        .filters(f -> f.setPath("/graphql"))
                        .uri("http://localhost:9090"))
                .route(p -> p
                        .path("/user/graphql")
                        .filters(f -> f.setPath("/graphql"))
                        .uri("http://localhost:1010"))
                .build();
    }
}
