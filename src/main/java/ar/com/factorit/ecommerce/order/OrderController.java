package ar.com.factorit.ecommerce.order;

import ar.com.factorit.ecommerce.common.ApiResponse;
import ar.com.factorit.ecommerce.security.AuthenticationService;
import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/placeorder")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token) {
        User user = authenticationService.authenticate(token);
        orderService.placeOrder(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"order has been placed"));

    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
            @RequestParam("token") String token,
            @RequestParam(value = "from",defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
            @RequestParam(value = "to",defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to,
            @RequestParam(value = "orderByDate", defaultValue = "true") Boolean orderByDate,
            @RequestParam(value = "orderByTotal",defaultValue = "false") Boolean orderByTotal

            ) {

        try {
            User user = authenticationService.authenticate(token);
            List<Order> orders = orderService.getOrders(user,from,to,orderByDate,orderByTotal);
            return ResponseEntity.ok(orders);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }

    }

}
