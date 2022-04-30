package mk.ukim.finki.model.exceptions;

public class PasswordIsTooShortException extends RuntimeException{
    public PasswordIsTooShortException() {
        super(String.format("The password is too short."));
    }
}
