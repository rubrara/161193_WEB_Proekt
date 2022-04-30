package mk.ukim.finki.service;

import mk.ukim.finki.model.Product;
import mk.ukim.finki.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);

    void removeProductFromCart(ShoppingCart shoppingCart, Long id);

}
