package ge.ecomerce.newecomerce.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 36, nullable = false, updatable = false)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer saleInPercent;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal saleInNumber;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Product> products;

}
