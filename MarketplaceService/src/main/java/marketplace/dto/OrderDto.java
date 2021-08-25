package marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import marketplace.models.Order;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {

    private Long id;

    private List<ProductDto> products;

    private String customerEmail;

    public static OrderDto from(Order order) {
        OrderDto result = OrderDto.builder()
                .id(order.getId())
                .customerEmail(order.getCustomerEmail())
                .build();
        if (order.getProducts() != null) {
            result.setProducts(ProductDto.from(order.getProducts()));
        }

        return result;
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }

}
