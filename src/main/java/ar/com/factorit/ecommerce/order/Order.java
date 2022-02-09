package ar.com.factorit.ecommerce.order;

import ar.com.factorit.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name="payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItem> orderItems;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;


    @Column(name = "used_vip_discount")
    private boolean usedVipDiscount;






}
