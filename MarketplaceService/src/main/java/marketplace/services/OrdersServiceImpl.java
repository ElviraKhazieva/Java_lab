package marketplace.services;

import marketplace.dto.OrderDto;
import marketplace.dto.ProductDto;
import marketplace.models.Order;
import marketplace.models.Product;
import marketplace.repositories.OrdersRepository;
import marketplace.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static marketplace.dto.OrderDto.from;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public OrderDto addOrder(OrderDto order) {
        LocalDateTime creation = LocalDateTime.now();
        Order newOrder = Order.builder()
                .number(creation.hashCode())
                .creation(creation)
                .customerEmail(order.getCustomerEmail())
                .build();
        if (order.getProducts() != null) {
            newOrder.setProducts(productsRepository.findAllById(order.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())));
        }
        ordersRepository.save(newOrder);
        return from(newOrder);
    }

    @Override
    public List<OrderDto> getOrdersByEmail(String email) {
        return from(ordersRepository.findOrdersByCustomerEmail(email));
    }

    @Override
    public List<OrderDto> getOrdersInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return from(ordersRepository.findOrdersByCreationBetween(startDate, endDate));
    }

    @Override
    public List<OrderDto> getOrdersContainsProductArticle(String article) {
        Product product = productsRepository.findByArticle(article).orElseThrow(IllegalArgumentException::new);
        return from(ordersRepository.findOrdersByProductsContains(product));
    }

}
