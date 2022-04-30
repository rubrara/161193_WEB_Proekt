package mk.ukim.finki.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException() {
        super(String.format("The username and/or password are invalid."));
    }
}
