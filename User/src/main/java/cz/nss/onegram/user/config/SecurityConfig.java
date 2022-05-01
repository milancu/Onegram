package cz.nss.onegram.user.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //authorize all graphQL queries as we will filter request at the resolvers level
                .antMatchers("/graphql").permitAll()

                //authorize requests from graphql related apps that we will need
                .antMatchers("/graphiql").permitAll()
                .antMatchers("/vendor/**").permitAll()

                //any other requests should be authenticated
                .anyRequest().authenticated();
    }
}
