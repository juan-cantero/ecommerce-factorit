package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.common.ApiResponse;
import ar.com.factorit.ecommerce.product.Product;
import ar.com.factorit.ecommerce.product.ProductNotExistException;
import ar.com.factorit.ecommerce.product.ProductService;
import ar.com.factorit.ecommerce.security.AuthenticationFailException;
import ar.com.factorit.ecommerce.security.AuthenticationService;
import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("isSpecial") Boolean isSpecial,
                                                 @RequestParam("token") String token) {
        User user;
        try {
            user = authenticationService.authenticate(token);
            cartService.addTocart(addToCartDto, user,isSpecial);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"added to cart"));

        }catch (AuthenticationFailException | ProductNotExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);

        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItemDto>> getItemsFromOpenCartsByUser(@RequestParam("token") String token) {
        try {
            User user = authenticationService.authenticate(token);
            List<CartItemDto> cartItems =  cartService.getItemsFromOpenCartByUser(user);
            return ResponseEntity.ok(cartItems);
        }catch (AuthenticationFailException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteItemFromCart(@RequestParam("token") String token,
                                                          @PathVariable("productId") Integer productId) {
        try {
            User user = authenticationService.authenticate(token);
            Product product = productService.findProductById(productId);
            cartService.deleteProductFromCart(user,product);
            return ResponseEntity.ok(new ApiResponse(true,"Item deleted succesfully"));
        } catch (ProductNotExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }

    }


}
