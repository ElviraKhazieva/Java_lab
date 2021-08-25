package marketplace.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import marketplace.dto.ProductDto;
import marketplace.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @ApiOperation(value = "Получение всех товаров/услуг")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено", response = ProductDto.class)})
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @ApiOperation(value = "Добавление товара/услуги")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = ProductDto.class)})
    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productsService.addProduct(product));
    }

    @ApiOperation(value = "Обновление товара/услуги")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно обновлено", response = ProductDto.class)})
    @PutMapping("/products/{product-id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("product-id") Long productId, @RequestBody ProductDto product) {
        return ResponseEntity.ok(productsService.updateProduct(productId, product));
    }

    @ApiOperation(value = "Удаление товара/услуги")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @DeleteMapping("/products/{product-id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product-id") Long productId) {
        productsService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

}
