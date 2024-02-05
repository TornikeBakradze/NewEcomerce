package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.user.Roles;
import ge.ecomerce.newecomerce.entity.user.Users;
import ge.ecomerce.newecomerce.exception.UserAlreadyExistException;
import ge.ecomerce.newecomerce.model.request.UserLogin;
import ge.ecomerce.newecomerce.model.request.UserModel;
import ge.ecomerce.newecomerce.model.respone.LoginResponse;
import ge.ecomerce.newecomerce.repository.RoleRepository;
import ge.ecomerce.newecomerce.repository.UserRepository;
import ge.ecomerce.newecomerce.service.AuthenticationService;
import ge.ecomerce.newecomerce.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public Users userRegistration(UserModel userModel) {
        try {
            String encodedPassword = passwordEncoder.encode(userModel.getPassword());

            Roles roles = roleRepository.findByAuthority("USER").get();

            Set<Roles> rolesSet = new HashSet<>();
            rolesSet.add(roles);

            Users user = Users.builder()
                    .name(userModel.getName())
                    .lastname(userModel.getLastname())
                    .email(userModel.getEmail())
                    .password(encodedPassword)
                    .number(userModel.getNumber())
                    .authorities(rolesSet)
                    .build();

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistException("User already exist");
        }
    }

    @Override
    public LoginResponse loginUser(UserLogin userRegister) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegister.getEmail(), userRegister.getPassword()));
            String token = tokenService.generateJWt(auth);
            return new LoginResponse(userRepository.findByEmail(userRegister.getEmail()).get(), token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
