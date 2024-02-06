package ge.ecomerce.newecomerce.exception;


public class UpdatePasswordException extends RuntimeException{
    public UpdatePasswordException(String message) {
        super(message);
    }

    public UpdatePasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
