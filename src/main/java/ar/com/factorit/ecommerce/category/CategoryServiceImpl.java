package ar.com.factorit.ecommerce.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void editCategory(int categoryId, Category categoryUpdate) {
        Category category = categoryRepository.getById(categoryId);
        category.setCategoryName(categoryUpdate.getCategoryName());
        category.setDescription(categoryUpdate.getDescription());
        category.setImageUrl(categoryUpdate.getImageUrl());
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public boolean checkIfCategoryExistById(int categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
