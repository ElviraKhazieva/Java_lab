package marketplace.repositories;

import marketplace.models.Order;
import marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByNumber(Integer number);

    List<Order> findOrdersByCustomerEmail(String email);

    List<Order> findOrdersByCreationBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findOrdersByProductsContains(Product product);

}
