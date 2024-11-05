package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.User;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserLoginDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegisterDto;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserRegistrationDto;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.service.UserService;

@Component
@AllArgsConstructor
public class UserDtoMapper implements ApplicationContextAware {

    private static UserService userService;

    public static User mapUserRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        return User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();
    }

    public static User mapUserLoginDtoToUser(UserLoginDto userLoginDto) {
        return User.builder()
                .username(userLoginDto.getUsername())
                .password(userLoginDto.getPassword())
                .build();
    }

    public static UserRegistrationDto mapUserRegisterDtoToUserRegistrationDto(User registeredUser) {
        return UserRegistrationDto.builder()
                .username(registeredUser.getUsername())
                .email(registeredUser.getEmail())
                .build();
    }

    public static JwtAuthResponse mapUserLoginDtoToJwtAuthResponse(String token) {
        return JwtAuthResponse.builder().accessToken(token).tokenType("Bearer").build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        userService = applicationContext.getBean(UserService.class);
    }
}
