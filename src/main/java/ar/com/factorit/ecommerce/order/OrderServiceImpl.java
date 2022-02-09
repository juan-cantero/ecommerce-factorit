package ar.com.factorit.ecommerce.order;

import ar.com.factorit.ecommerce.cart.CartItemDto;
import ar.com.factorit.ecommerce.cart.CartService;
import ar.com.factorit.ecommerce.totalcostcalculator.CostCalculator;
import ar.com.factorit.ecommerce.totalcostcalculator.TotalCostCalculatorBuilder;
import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void placeOrder(User user) {
        List<CartItemDto> items = cartService.getItemsFromOpenCartByUser(user);

        int totalItems = items.stream().mapToInt(CartItemDto::getQuantity).sum();

        boolean isVipClient = isVipClient(user);

        boolean isSpecialCart = cartService.isSpecialCart(user);

        CostCalculator totalCostCalculator = TotalCostCalculatorBuilder
                .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,true);


        BigDecimal totalCost =  totalCostCalculator.getTotalCost(items);

        boolean hasUsedVipDiscount = isVipClient;

        Order newOrder = Order.builder()
                .createdDate(LocalDate.now())
                .user(user)
                .usedVipDiscount(hasUsedVipDiscount)
                .paymentMethod(PaymentMethod.CASH)
                .transactionNumber(UUID.randomUUID().toString())
                .totalPrice(totalCost)
                .build();

        orderRepository.save(newOrder);

        for (CartItemDto item: items) {
            OrderItem orderItem = OrderItem.builder()
                    .createdDate(LocalDate.now())
                    .product(item.getProduct())
                    .quantity(item.getQuantity())
                    .order(newOrder)
                    .build();

            orderItemRepository.save(orderItem);
        }

        cartService.closeCart(user);


    }



    @Override
    public List<Order> getOrders(User user, LocalDate from, LocalDate to, Boolean orderByDate, Boolean orderByTotal) {



        List<Sort.Order> orders = new ArrayList<>();

        if(orderByDate) {
            orders.add(new Sort.Order(Sort.Direction.DESC,"createdDate"));
        }
        if (orderByTotal) {
            orders.add(new Sort.Order(Sort.Direction.DESC,"totalPrice"));
        }

        return orderRepository
                .findByUserAndCreatedDateBetween(user,from,to, Sort.by(orders));
    }

    private boolean isVipClient(User user) {
        ZoneId z = ZoneId.of(TimeZone.getDefault().getID());
        LocalDate today = LocalDate.now( z );
        YearMonth currentMonth = YearMonth.now( z ) ;

        LocalDate start = currentMonth.atDay( 1 ) ;

        LocalDate stop = start.plusMonths( 1 ).minusDays(1);

        List<Order> orders = orderRepository.findByUserAndCreatedDateBetween(user,start,stop,Sort.by(Sort.Direction.ASC,"createdDate"));

        // si ya se uso el descuento vip de el mes devuelve falso
        if (orders.stream().anyMatch(o -> o.isUsedVipDiscount())) {
            return false;
        }

        // devuelve true si las ordenes del mes superan los 5 mil pesos
        return orders.stream()
                .map(o -> o.getTotalPrice())
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .compareTo(BigDecimal.valueOf(5000)) > 0;
    }
}
