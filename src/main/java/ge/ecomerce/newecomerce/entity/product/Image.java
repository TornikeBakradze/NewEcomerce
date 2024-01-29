package ge.ecomerce.newecomerce.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String name;

    private String type;

    private String filePath;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Product product;

    public Image() {
    }

    public Image(String name, String type, String filePath) {
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }
}
