package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.product.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDto(Cart cart) {
        this.setId(cart.getCartId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
