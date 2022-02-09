package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.product.Product;
import ar.com.factorit.ecommerce.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUserAndIsOpenEqualsOrderByCreatedDateDesc(User user, boolean isOpen);

    @Modifying
    @Query("Update Cart c set c.isOpen = false where c.user = :user")
    void closeCart(@Param("user") User user);

    void deleteByUserAndProductAndIsOpenEquals(User user, Product product, boolean isOpen);
}
