package ge.ecomerce.newecomerce.entity;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

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

    private BigDecimal price;

    private Integer quantity;

    private String details;

    private String description;

    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subcategory subcategory;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
