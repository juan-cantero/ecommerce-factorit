package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
