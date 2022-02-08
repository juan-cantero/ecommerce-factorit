package ar.com.factorit.ecommerce.category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);

    List<Category> listCategories();

    void editCategory(int categoryId, Category categoryUpdate);

    Optional<Category> findCategoryById(int categoryId);

    boolean checkIfCategoryExistById(int categoryId);
}
