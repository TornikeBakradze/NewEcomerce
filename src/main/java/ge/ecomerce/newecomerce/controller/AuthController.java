package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.model.request.UserLogin;
import ge.ecomerce.newecomerce.model.request.UserModel;
import ge.ecomerce.newecomerce.model.respone.LoginResponse;
import ge.ecomerce.newecomerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/singup")
    public ResponseEntity<Users> newUser(@RequestBody UserModel userModel) {
        return new ResponseEntity<>
                (authenticationService.userRegistration(userModel), HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody UserLogin userRegister) {
        return authenticationService.loginUser(userRegister);
    }

}
