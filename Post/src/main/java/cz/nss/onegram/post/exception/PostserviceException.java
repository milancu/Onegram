package cz.nss.onegram.post.exception;

/**
 * Base for all application-specific exceptions.
 */
public class PostserviceException extends RuntimeException {

    public PostserviceException() {
    }

    public PostserviceException(String message) {
        super(message);
    }

    public PostserviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostserviceException(Throwable cause) {
        super(cause);
    }
}