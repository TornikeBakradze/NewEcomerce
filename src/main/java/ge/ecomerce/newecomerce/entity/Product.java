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

    @NotNull(message = "Product name must no be null")
    @NotBlank(message = "Product name must not be null")
    @Size(min = 2, max = 50, message = "Product name must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Product name must contain only letters and number")
    @Column(unique = true)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;


    @Size(max = 1000, message = "the description is to long")
    @Pattern(regexp = "^[a-zA-Z0-9 %.,!]+$", message = "Description can only contains letters numbers and % , . ! symbol")
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subcategory subcategory;

    private BigDecimal price;

    private int quantity;




}
