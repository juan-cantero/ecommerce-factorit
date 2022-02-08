package ar.com.factorit.ecommerce.category;

import ar.com.factorit.ecommerce.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,""));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.listCategories());
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId,
                                                      @RequestBody Category category) {
        try {
            categoryService.editCategory(categoryId,category);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"category has been updated"));
        }catch (Exception e) {
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server error",e);
        }
    }


}
