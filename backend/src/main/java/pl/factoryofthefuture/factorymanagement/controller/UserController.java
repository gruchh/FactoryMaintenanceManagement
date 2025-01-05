package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegistrationDto;
import pl.factoryofthefuture.factorymanagement.mapper.UserDtoMapper;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.factoryofthefuture.factorymanagement.mapper.UserDtoMapper.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            String jwtToken = userService.register(userDtoMapper.mapUserRegistrationDtoToEntity(userRegisterDto));
            return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.mapTokenToJwtAuthResponse(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDtoMapper.mapFailedAuthResponse("Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserLoginDto userLoginDto) {
        String jwtToken = userService.verify(userDtoMapper.mapUserLoginDtoToEntity(userLoginDto));
        return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.mapTokenToJwtAuthResponse(jwtToken));
    }

    @GetMapping("/me")
    public Set<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return roles;
    }
}
