package ar.com.factorit.ecommerce.order;

import ar.com.factorit.ecommerce.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserAndCreatedDateBetween(User user, LocalDate from, LocalDate to, Sort sort);
}
