package ar.com.factorit.ecommerce.cart;

import ar.com.factorit.ecommerce.product.Product;
import ar.com.factorit.ecommerce.product.ProductNotExistException;
import ar.com.factorit.ecommerce.product.ProductService;
import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public void addTocart(AddToCartDto addToCartDto, User user, Boolean isSpecial) throws ProductNotExistException {

        Product product = productService.findProductById(addToCartDto.getProductId());

        Cart cart = Cart.builder()
                .isSpecial(isSpecial)
                .isOpen(true)
                .product(product)
                .user(user)
                .quantity(addToCartDto.getQuantity())
                .createdDate(LocalDate.now())
                .build();
        cartRepository.save(cart);


    }

    @Override
    public void deleteProductFromCart(User user,Product product) {
        cartRepository.deleteByUserAndProductAndIsOpenEquals(user,product,true);
    }


    @Override
    public List<CartItemDto> getItemsFromOpenCartByUser(User user) {
        List<Cart> carts = cartRepository.findAllByUserAndIsOpenEqualsOrderByCreatedDateDesc(user,true);

        return carts.stream().map(CartItemDto::new).collect(Collectors.toList());
    }

    @Override
    public long countTotalProductsFromCartByUser(User user) {
        return getItemsFromOpenCartByUser(user).size();
    }


    @Override
    public void closeCart(User user) {
        cartRepository.closeCart(user);
    }

    @Override
    public boolean isSpecialCart(User user) {
        List<Cart> carts = cartRepository.findAllByUserAndIsOpenEqualsOrderByCreatedDateDesc(user,true);
        if (!carts.isEmpty()) {
            return carts.get(0).isSpecial();
        }

        return false;
    }
}
