package marketplace.repositories;

import marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsDeletedIsNull();

    Optional<Product> findByArticle(String article);

}
