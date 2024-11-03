package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.config.security.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegisterDto userRegisterDto) {
        User register = userService.register(userRegisterDto);
        return register;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.verify(userLoginDto);
        return ResponseEntity.status(HttpStatus.OK).body(JwtAuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build());
    }
}
