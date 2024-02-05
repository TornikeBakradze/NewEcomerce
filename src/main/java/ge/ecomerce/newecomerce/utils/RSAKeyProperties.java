package ge.ecomerce.newecomerce.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Getter
@Setter
public class RSAKeyProperties {
    private RSAPublicKey rsaPublicKey;

    private RSAPrivateKey rsaPrivateKey;

    public RSAKeyProperties() {
        KeyPair keyPair = KeyGeneratorUtility.generateRSAKey();
        this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        this.rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
    }
}
