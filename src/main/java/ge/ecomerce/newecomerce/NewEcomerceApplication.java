package ge.ecomerce.newecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewEcomerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewEcomerceApplication.class, args);
    }

}
