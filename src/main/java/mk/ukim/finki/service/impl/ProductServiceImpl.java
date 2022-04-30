package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.Material;
import mk.ukim.finki.model.Product;
import mk.ukim.finki.model.exceptions.InvalidImageException;
import mk.ukim.finki.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.model.exceptions.ProductAlreadyExistsException;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.ProductRepository;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, String description, Category category, Material material, MultipartFile multipartFile, String username) {

        // if name product exist vrati nekoja poraka deka postoj takov produkt
        if (productRepository.findByName(name).isPresent())
            throw new ProductAlreadyExistsException(name);

        Author author = userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException()).getAuthor();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Product p = new Product();

        if (fileName.contains("..")) throw new InvalidImageException();



        try {
            p.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setPrice(price);
        p.setDescription(description);
        p.setCategory(category);
        p.setAuthor(author);
        p.setMaterial(material);
        p.setName(name);

        return Optional.of(productRepository.save(p));



    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, String description, Category category, Material material, MultipartFile file, String username) {

        Product product = findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Author author = userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException()).getAuthor();

        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        if (material != null)
            product.setMaterial(material);
        if (category != null)
            product.setCategory(category);


        return Optional.of(productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public List<Product> listAllProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> listAllProductsByMaterial(Material material) {
        return productRepository.findByMaterial(material);
    }

    @Override
    public List<Product> listAllProductsByAuthor(Author author) {
        return productRepository.findByAuthor(author);
    }


}
