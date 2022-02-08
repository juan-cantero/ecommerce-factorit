package ar.com.factorit.ecommerce.product;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull BigDecimal price;
    private @NotNull String description;
    private @NotNull Integer categoryId;



}
