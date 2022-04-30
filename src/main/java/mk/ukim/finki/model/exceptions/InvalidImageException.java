package mk.ukim.finki.model.exceptions;

public class InvalidImageException extends RuntimeException{
    public InvalidImageException() {
        super(String.format("The image is not a valid file."));
    }
}
