package mk.ukim.finki.service;

import mk.ukim.finki.model.Category;

import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    // Creating a new category
    Category create(String name, String description);

    // Updating an existing category
    Category update(Long id, String name, String description);

    // Deleting a category
    void delete(Long id);

    // Listing of all existing categories
    List<Category> listCategories();

    // Listing categories matching or containing given searchText
    List<Category> searchCategories(String searchText);

}
