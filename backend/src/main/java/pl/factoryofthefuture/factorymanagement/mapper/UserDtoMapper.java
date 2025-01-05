package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
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

    public JwtAuthResponse mapTokenToJwtAuthResponse(String token) {
        return JwtAuthResponse.builder().accessToken(token).build();
    }

    public JwtAuthResponse mapFailedAuthResponse(String message) {
        return JwtAuthResponse.builder().accessToken(message).build();
    }
}
