package mk.ukim.finki.model.exceptions;

public class InvalidMaterialIdException extends RuntimeException{
    public InvalidMaterialIdException(Long id) {
        super(String.format("The material with id: %d, doesn't exist.", id));
    }


}
