package mk.ukim.finki.model.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name) {
        super(String.format("The product with name: %s, already exists.", name));
    }
}
