package ar.com.factorit.ecommerce.product;

import ar.com.factorit.ecommerce.category.Category;
import ar.com.factorit.ecommerce.category.CategoryNotExistException;
import ar.com.factorit.ecommerce.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void createProduct(ProductDto productDto) {

        Optional<Category> categoryOptional = categoryService.findCategoryById(productDto.getCategoryId());


        if(categoryOptional.isEmpty()) {
            throw new CategoryNotExistException("There is not such category");
        }

        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageURL())
                .price(productDto.getPrice())
                .category(categoryOptional.get())
                .build();

        productRepository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(int id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotExistException();
        }
        return productOptional.get();
    }
}
