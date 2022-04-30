package mk.ukim.finki.model.exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException() {
        super(String.format("The username is taken."));
    }
}
