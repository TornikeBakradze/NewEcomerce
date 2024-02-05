package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.UserRepository;
import ge.ecomerce.newecomerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Users> getALLUser() {
        try {
            List<Users> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new DataNotFoundException("User not found");
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users getUserById(Long userID) {
        return userRepository.findById(userID).orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Override
    public List<Users> getUserByName(String name) {
        try {
            List<Users> users = userRepository.findByNameContaining(name);
            if (users.isEmpty()) {
                throw new DataNotFoundException("User not found");
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String addOrUpdateUserPhoneNumber(Long userId, String number) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            user.setNumber(number);
            userRepository.save(user);
            return "Number add successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateUserName(Long userId, String name) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            user.setName(name);
            userRepository.save(user);
            return "Number add successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateUserLastname(Long userId, String lastname) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            user.setLastname(lastname);
            userRepository.save(user);
            return "Number add successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateUserEmail(Long userId, String email) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            user.setEmail(email);
            userRepository.save(user);
            return "Number add successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateUserPassword(Long userId, String password) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "Number add successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteUser(Long userId) {
        try {
            Users user =
                    userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User not found"));
            userRepository.delete(user);
            return "User acaunt is deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
