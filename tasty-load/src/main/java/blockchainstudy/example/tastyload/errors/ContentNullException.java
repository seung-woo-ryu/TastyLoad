package blockchainstudy.example.tastyload.errors;

public class ContentNullException extends RuntimeException {

    public ContentNullException(String message) {

        super(message);
    }

    public ContentNullException(String message, Throwable cause) {
        super(message, cause);
    }

}
