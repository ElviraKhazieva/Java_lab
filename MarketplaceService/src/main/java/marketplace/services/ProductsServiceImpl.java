package marketplace.services;

import marketplace.dto.ProductDto;
import marketplace.models.Product;
import marketplace.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static marketplace.dto.ProductDto.from;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return from(productsRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public ProductDto addProduct(ProductDto product) {
        Product newProduct = Product.builder()
                .article(UUID.randomUUID().toString())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        productsRepository.save(newProduct);
        return from(newProduct);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto product) {
        Product productForUpdate = productsRepository.findById(productId)
                .orElseThrow(IllegalArgumentException::new);
        productForUpdate.setName(product.getName());
        productForUpdate.setPrice(product.getPrice());
        productsRepository.save(productForUpdate);
        return from(productForUpdate);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product productForDelete = productsRepository.findById(productId)
                .orElseThrow(IllegalArgumentException::new);
        productForDelete.setIsDeleted(true);
        productsRepository.save(productForDelete);
    }

}
