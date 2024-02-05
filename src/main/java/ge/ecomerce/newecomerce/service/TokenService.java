package ge.ecomerce.newecomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;


public interface TokenService {
    String generateJWt(Authentication auth);
}
