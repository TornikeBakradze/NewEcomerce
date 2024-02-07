package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/addPrivileges/{userId}")
    public ResponseEntity<String> addPrivileges(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(adminService.addPrivileges(userId), HttpStatus.OK);
    }
}
