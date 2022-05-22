package config;

import graphql.schema.idl.SchemaParser;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public SchemaParser schemaParser(){
        return Mockito.mock(SchemaParser.class);
    }
}
