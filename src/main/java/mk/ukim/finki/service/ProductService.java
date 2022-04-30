package mk.ukim.finki.service;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price, String description, Category category, Material material, MultipartFile file, String username);

    Optional<Product> edit(Long id, String name, Double price, String description, Category category, Material material, MultipartFile file, String username);

    void deleteById(Long id);

    List<Product> listAllProductsByCategory(Category category);
    List<Product> listAllProductsByMaterial(Material material);
    List<Product> listAllProductsByAuthor(Author author);




}
