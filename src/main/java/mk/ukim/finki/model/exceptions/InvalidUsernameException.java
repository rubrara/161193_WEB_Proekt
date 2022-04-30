package mk.ukim.finki.model.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super(String.format("Invalid username"));
    }
}
