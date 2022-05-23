package cz.nss.onegram.user.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
