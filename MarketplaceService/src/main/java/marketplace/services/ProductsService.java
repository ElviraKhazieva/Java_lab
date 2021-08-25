package marketplace.services;

import marketplace.dto.ProductDto;
import java.util.List;

public interface ProductsService {

    List<ProductDto> getAllProducts();

    ProductDto addProduct(ProductDto product);

    ProductDto updateProduct(Long productId, ProductDto product);

    void deleteProduct(Long productId);
}
