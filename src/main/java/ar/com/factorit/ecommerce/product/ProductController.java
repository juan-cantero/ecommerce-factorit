package ar.com.factorit.ecommerce.product;

import ar.com.factorit.ecommerce.category.CategoryService;
import ar.com.factorit.ecommerce.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        try {

            productService.createProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"product created"));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server error",e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productService.listProducts();

        return ResponseEntity.ok().body(products);
    }


}
