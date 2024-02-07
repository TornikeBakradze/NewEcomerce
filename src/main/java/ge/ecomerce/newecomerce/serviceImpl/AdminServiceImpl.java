package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.user.Roles;
import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.RoleRepository;
import ge.ecomerce.newecomerce.repository.UserRepository;
import ge.ecomerce.newecomerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public String addPrivileges(Long userID) {
        try {
            Users user = userRepository.findById(userID).orElseThrow(() -> new DataNotFoundException("User not found"));

            Roles role = roleRepository.findByAuthority("STAFF").orElseThrow(() -> new DataNotFoundException("Role not found"));
            Set<Roles> roles = new HashSet<>();
            roles.add(role);
            user.setAuthorities(roles);

            userRepository.save(user);

            return String.format("%s user become %s", user.getUsername(), role.getAuthority());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
