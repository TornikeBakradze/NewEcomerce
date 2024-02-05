package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        return userRepository.findByEmail(username).orElseThrow(() -> new DataNotFoundException("User not found"));
    }
}
