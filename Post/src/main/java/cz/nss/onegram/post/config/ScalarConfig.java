package cz.nss.onegram.post.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GraphQL extended scalars
 */
@Configuration
public class ScalarConfig {
    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType localTime() {
        return ExtendedScalars.LocalTime;
    }
}
