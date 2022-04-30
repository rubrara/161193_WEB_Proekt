package mk.ukim.finki.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("The product with id: %d, doesn't exist", id));
    }
}
