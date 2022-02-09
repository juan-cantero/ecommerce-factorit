package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.product.Product;
import ar.com.factorit.ecommerce.product.ProductNotExistException;
import ar.com.factorit.ecommerce.user.User;

import java.util.List;

public interface CartService {
    void addTocart(AddToCartDto addToCartDto, User user, Boolean isSpecial) throws ProductNotExistException;

    void deleteProductFromCart(User user, Product product);

    List<CartItemDto> getItemsFromOpenCartByUser(User user);

    long countTotalProductsFromCartByUser(User user);



    void closeCart(User user);

    boolean isSpecialCart(User user);
}
