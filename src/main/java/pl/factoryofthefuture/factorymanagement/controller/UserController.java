package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegistrationDto;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.service.UserService;

import static pl.factoryofthefuture.factorymanagement.mapper.UserDtoMapper.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        User registeredUser = userService.register(mapUserRegisterDtoToUser(userRegisterDto));
        return ResponseEntity.status(HttpStatus.OK).body(mapUserRegisterDtoToUserRegistrationDto(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.verify(mapUserLoginDtoToUser(userLoginDto));
        return ResponseEntity.status(HttpStatus.OK).body(mapUserLoginDtoToJwtAuthResponse(token));
    }
}
