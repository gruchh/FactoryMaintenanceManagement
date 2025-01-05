package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegistrationDto;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.service.UserService;

@Component
@Data
@AllArgsConstructor
public class UserDtoMapper {

    private final UserService userService;

    public User mapUserRegistrationDtoToEntity(UserRegisterDto userRegisterDto) {
        return User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();
    }

    public User mapUserLoginDtoToEntity(UserLoginDto userLoginDto) {
        return User.builder()
                .username(userLoginDto.getUsername())
                .password(userLoginDto.getPassword())
                .build();
    }

    public UserRegistrationDto mapUserToUserRegistrationDto(User registeredUser) {
        return UserRegistrationDto.builder()
                .username(registeredUser.getUsername())
                .email(registeredUser.getEmail())
                .build();
    }

    public JwtAuthResponse mapTokenToJwtAuthResponse(String token) {
        return JwtAuthResponse.builder().accessToken(token).tokenType("Bearer").build();
    }
}
