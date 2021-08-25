package marketplace.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import marketplace.dto.OrderDto;
import marketplace.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "Получение заказов по определенному email")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено", response = OrderDto.class)})
    @GetMapping(value = "/orders", params = {"email"})
    public ResponseEntity<List<OrderDto>> getOrdersByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(ordersService.getOrdersByEmail(email));
    }

    @ApiOperation(value = "Получение заказов за определенный период времени")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено", response = OrderDto.class)})
    @GetMapping(value = "/orders", params = {"startDate", "endDate"})
    public ResponseEntity<List<OrderDto>> getOrdersInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(ordersService.getOrdersInDateRange(startDate, endDate));
    }

    @ApiOperation(value = "Получение заказов, в которых есть товар с определенным артикулом")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено", response = OrderDto.class)})
    @GetMapping(value = "/orders", params = {"article"})
    public ResponseEntity<List<OrderDto>> getOrdersContainsProductArticle(@RequestParam("article") String article) {
        return ResponseEntity.ok(ordersService.getOrdersContainsProductArticle(article));
    }

    @ApiOperation(value = "Добавление заказа")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = OrderDto.class)})
    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto order) {
        return ResponseEntity.ok(ordersService.addOrder(order));
    }

}
