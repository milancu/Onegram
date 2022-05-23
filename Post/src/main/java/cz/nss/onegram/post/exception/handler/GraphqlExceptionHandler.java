package cz.nss.onegram.post.exception.handler;

import cz.nss.onegram.post.exception.PostserviceException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@Component
public class GraphqlExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ThrowableGraphQLError handle(NoSuchElementException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(InputMismatchException.class)
    public ThrowableGraphQLError handle(InputMismatchException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ThrowableGraphQLError handle(UsernameNotFoundException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(PostserviceException.class)
    public ThrowableGraphQLError handle(PostserviceException e){
        return new ThrowableGraphQLError(e);
    }
}
