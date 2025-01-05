package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.Role;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.repository.RoleRepository;
import pl.factoryofthefuture.factorymanagement.repository.UserRepository;
import pl.factoryofthefuture.factorymanagement.security.service.JwtService;

import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String register(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new IllegalStateException("User with this email already exists");
        });
        userRepository.findByUsername(user.getUsername()).ifPresent(existingUser -> {
            throw new IllegalStateException("User with this username already exists");
        });
        String originalPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException("Role USER not found"));
        user.setRoles(Set.of(role));
        userRepository.save(user);
        User userToLogin = User.builder()
                .username(user.getUsername())
                .password(originalPassword)
                .build();
//        userToLogin.setUsername(user.getUsername());
//        userToLogin.setPassword(originalPassword);
        return verify(userToLogin);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}
