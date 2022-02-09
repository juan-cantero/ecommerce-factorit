package ar.com.factorit.ecommerce.order;

import ar.com.factorit.ecommerce.user.User;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void placeOrder(User user);

    List<Order> getOrders(User user, LocalDate from, LocalDate to, Boolean orderByDate, Boolean orderByTotal);


}
