package marketplace.services;

import marketplace.dto.OrderDto;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersService {

    OrderDto addOrder(OrderDto order);

    List<OrderDto> getOrdersByEmail(String email);

    List<OrderDto> getOrdersInDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDto> getOrdersContainsProductArticle(String article);

}
