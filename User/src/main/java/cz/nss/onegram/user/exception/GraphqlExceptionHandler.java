package cz.nss.onegram.user.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class GraphqlExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ThrowableGraphQLError handle(UsernameNotFoundException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ThrowableGraphQLError handle(NoSuchElementException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(InputMismatchException.class)
    public ThrowableGraphQLError handle(InputMismatchException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(MessageServiceException.class)
    public ThrowableGraphQLError handle(MessageServiceException e){
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(UserServiceException.class)
    public ThrowableGraphQLError handle(UserServiceException e){
        return new ThrowableGraphQLError(e);
    }

}
