package ar.com.factorit.ecommerce.cart;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddToCartDto {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull boolean isSpecial;
    private @NotNull Integer quantity;
}
