package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    List<Users> findByNameContaining(String name);
}
