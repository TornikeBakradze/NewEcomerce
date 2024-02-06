package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordChangeModel {

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String currentPassword;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String newPassword;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String confirmPassword;
}
