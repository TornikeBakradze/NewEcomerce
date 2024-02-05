package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.user.Users;

import java.util.List;

public interface UserService {
    List<Users> getALLUser();

    Users getUserById(Long userID);

    List<Users> getUserByName(String name);

    String addOrUpdateUserPhoneNumber(Long userId, String number);

    String updateUserName(Long userId, String name);

    String updateUserLastname(Long userId, String lastname);

    String updateUserEmail(Long userId, String email);

    String updateUserPassword(Long userId, String password);


    String deleteUser(Long userId);
}
