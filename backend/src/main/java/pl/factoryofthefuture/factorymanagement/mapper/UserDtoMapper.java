package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserDto;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.service.UserService;

@Component
@Data
@AllArgsConstructor
public class UserDtoMapper {

    private final UserService userService;

    public User mapUserDtoToEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }

    public JwtAuthResponse mapTokenToJwtAuthResponse(String token) {
        return JwtAuthResponse.builder().accessToken(token).build();
    }

    public JwtAuthResponse mapFailedAuthResponse(String message) {
        return JwtAuthResponse.builder().accessToken(message).build();
    }
}
