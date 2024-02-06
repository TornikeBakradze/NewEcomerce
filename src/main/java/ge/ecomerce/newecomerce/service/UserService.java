package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.model.request.PasswordChangeModel;

import java.util.List;

public interface UserService {
    List<Users> getALLUser();

    Users getUserById(Long userID);

    List<Users> getUserByName(String name);

    String addOrUpdateUserPhoneNumber(Long userId, String number);

    String updateUserName(Long userId, String name);

    String updateUserLastname(Long userId, String lastname);

    String updateUserEmail(Long userId, String email);

    String updateUserPassword(Long userId, PasswordChangeModel passwordChangeModel);


    String deleteUser(Long userId);
}
