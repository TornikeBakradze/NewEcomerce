package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.service.UserService;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/allUser")
    public ResponseEntity<List<Users>> getALlUser() {
        return new ResponseEntity<>(userService.getALLUser(), HttpStatus.OK);
    }

    @GetMapping("/getByID/{userID}")
    public ResponseEntity<Users> getByID(@PathVariable("userID") Long userID) {
        return new ResponseEntity<>(userService.getUserById(userID), HttpStatus.OK);
    }

    @GetMapping("/getByName/{userName}")
    public ResponseEntity<List<Users>> getByName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
    }

    @PutMapping("/addOrUpdateNumber/{userID}/{proneNumber}")
    public ResponseEntity<String> addOrUpdateUserPhoneNumber(@PathVariable("userID") Long userID,
                                                             @PathVariable("proneNumber")
                                                             @Pattern(regexp = "^[0-9]+$",
                                                                     message = "Phone number must contain only number")
                                                             String proneNumber) {
        return new ResponseEntity<>(userService.addOrUpdateUserPhoneNumber(userID, proneNumber), HttpStatus.OK);
    }

    @PutMapping("/updateUsername/{userID}/{username}")
    public ResponseEntity<String> updateUsername(@PathVariable("userID") Long userID,
                                                 @PathVariable("username")
                                                 @Pattern(regexp = "^[a-zA-Z0-9 -]+$",
                                                         message = "User name must contain only letters and number")
                                                 @Size(min = 2, max = 50, message = "User name must be 2 to 50")
                                                 String username) {
        return new ResponseEntity<>(userService.updateUserName(userID, username), HttpStatus.OK);
    }

    @PutMapping("/updateUseLastname/{userID}/{userLastname}")
    public ResponseEntity<String> updateUseLastname(@PathVariable("userID") Long userID,
                                                    @PathVariable("userLastname")
                                                    @Pattern(regexp = "^[a-zA-Z0-9 -]+$",
                                                            message = "User lastname must contain only letters and number")
                                                    @Size(min = 2, max = 50, message = "User name must be 2 to 50")
                                                    String userLastname) {
        return new ResponseEntity<>(userService.updateUserLastname(userID, userLastname), HttpStatus.OK);
    }

    @PutMapping("/updateUserPassword/{userID}/{userPassword}")
    public ResponseEntity<String> updateUsePassword(@PathVariable("userID") Long userID,
                                                    @PathVariable("userPassword")
                                                    @Size(min = 8, message = "Password must be at least 8 characters long")
                                                    String userPassword) {
        return new ResponseEntity<>(userService.updateUserPassword(userID, userPassword), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable("userID") Long userID) {
        return new ResponseEntity<>(userService.deleteUser(userID), HttpStatus.OK);
    }
}
