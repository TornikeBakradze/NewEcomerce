package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    @NotNull(message = "User name must no be null")
    @NotBlank(message = "User name must not be blank")
    @Size(min = 2, max = 50, message = "User name must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "User name must contain only letters and number")
    private String name;

    @NotNull(message = "User lastname must no be null")
    @NotBlank(message = "User lastname must not be blank")
    @Size(min = 2, max = 50, message = "User lastname must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "User lastname must contain only letters and number")
    private String lastname;


    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only number")
    private String number;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Bad email")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
