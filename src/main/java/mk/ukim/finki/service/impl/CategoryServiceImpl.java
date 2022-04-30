package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.repository.CategoryRepository;
import mk.ukim.finki.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new InvalidCategoryIdException(id));
    }

    @Override
    public Category create(String name, String description) {

        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category = new Category(name, description);
        categoryRepository.save(category);

        return category;
    }

    @Override
    public Category update(Long id, String name, String description) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        Category category = findById(id);

        category.setName(name);
        category.setDescription(description);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {

        Category cat = findById(id);
        categoryRepository.delete(cat);

    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return null;
    }
}
