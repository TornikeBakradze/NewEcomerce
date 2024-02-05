package ge.ecomerce.newecomerce.model.respone;

import ge.ecomerce.newecomerce.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private Users user;

    private String jwt;
}
