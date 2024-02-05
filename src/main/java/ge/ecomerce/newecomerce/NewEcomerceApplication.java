package ge.ecomerce.newecomerce;

import ge.ecomerce.newecomerce.entity.user.Roles;
import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.repository.RoleRepository;
import ge.ecomerce.newecomerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class NewEcomerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewEcomerceApplication.class, args);

    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Roles adminRole = Roles.builder()
                    .authority("ADMIN")
                    .build();
            Roles adminRoleCreate = roleRepository.save(adminRole);

            Roles userRole = Roles.builder()
                    .authority("USER")
                    .build();
            roleRepository.save(userRole);

            Set<Roles> roles = new HashSet<>();
            roles.add(adminRoleCreate);

            Users admin = Users.builder()
                    .name("Boss")
                    .email("boss@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .authorities(roles)
                    .build();

            userRepository.save(admin);
        };
    }

}
