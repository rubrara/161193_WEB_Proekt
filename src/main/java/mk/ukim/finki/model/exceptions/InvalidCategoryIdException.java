package mk.ukim.finki.model.exceptions;

import java.io.IOException;

public class InvalidCategoryIdException extends RuntimeException {
    public InvalidCategoryIdException(Long id) {
        super(String.format("The category with id: %d, doesn't exist.", id));
    }
}
