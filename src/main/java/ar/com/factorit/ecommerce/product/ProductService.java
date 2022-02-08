package ar.com.factorit.ecommerce.product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);

    List<Product> listProducts();

    Product findProductById(int id) throws ProductNotExistException;
}
