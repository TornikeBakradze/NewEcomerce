package ge.ecomerce.newecomerce.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.ecomerce.newecomerce.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 36, updatable = false, nullable = false)
    private long id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @Column(unique = true)
    @NotEmpty(message = "Name may not be empty")
    @Size(max = 50, message = "Subcategory name size must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -,]+$", message = "Product name must contain only letters and number")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcategory")
    @JsonIgnore
    private Set<Product> products;
}
