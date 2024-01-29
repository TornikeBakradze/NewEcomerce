package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);
}
