package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.config.security.JwtService;
import pl.factoryofthefuture.factorymanagement.entity.Role;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.repository.RoleRepository;
import pl.factoryofthefuture.factorymanagement.repository.UserRepository;

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

    public User register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new NoSuchElementException());
        return userRepository.save(User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .roles(Set.of(role))
                .build());
    }

    public String verify(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginDto.getUsername());
        } else {
            return "fail";
        }
    }
}
