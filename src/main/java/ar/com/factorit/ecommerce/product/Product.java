package ar.com.factorit.ecommerce.product;

import ar.com.factorit.ecommerce.cart.Cart;
import ar.com.factorit.ecommerce.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    private @NotNull String name;

    private @NotNull String imageUrl;

    private @NotNull BigDecimal price;

    private @NotNull String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="category_id")
    private Category category;



}
