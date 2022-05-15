package blockchainstudy.example.tastyload.errors;

public class DuplicatedUserException extends RuntimeException {

    public DuplicatedUserException(String message) {
        super(message);
    }

    public DuplicatedUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
