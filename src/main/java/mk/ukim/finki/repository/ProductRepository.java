package mk.ukim.finki.repository;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByMaterial(Material material);

    List<Product> findByAuthor(Author author);

}
