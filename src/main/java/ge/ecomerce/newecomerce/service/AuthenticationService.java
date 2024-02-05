package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.model.request.UserLogin;
import ge.ecomerce.newecomerce.model.request.UserModel;
import ge.ecomerce.newecomerce.model.respone.LoginResponse;

public interface AuthenticationService {

    Users userRegistration(UserModel userModel);

    LoginResponse loginUser(UserLogin userRegister);
}
