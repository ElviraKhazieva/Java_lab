package marketplace.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String article;

    private Boolean isDeleted;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Boolean isDeleted() {
        return isDeleted;
    }

}
