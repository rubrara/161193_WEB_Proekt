package mk.ukim.finki.model.exceptions;

public class InvalidMatchingPasswordException extends RuntimeException{
    public InvalidMatchingPasswordException() {
        super(String.format("The passwords do not match."));
    }
}
