package ge.ecomerce.newecomerce.entity.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 36, nullable = false, updatable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    private String brand;

    private BigDecimal price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal salePrice;

    private Integer quantity;

    private String details;

    private String description;

    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Sale sale;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subcategory subcategory;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Image> images;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
