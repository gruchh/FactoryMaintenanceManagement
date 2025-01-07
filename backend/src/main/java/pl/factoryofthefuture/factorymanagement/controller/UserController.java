package pl.factoryofthefuture.factorymanagement.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserDto;
import pl.factoryofthefuture.factorymanagement.mapper.UserDtoMapper;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.security.service.JwtService;
import pl.factoryofthefuture.factorymanagement.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody UserDto userDto) {
        try {
            String jwtToken = userService.register(userDtoMapper.mapUserDtoToEntity(userDto));
            return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.mapTokenToJwtAuthResponse(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDtoMapper.mapFailedAuthResponse("Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserDto userDto) {
        String jwtToken = userService.verify(userDtoMapper.mapUserDtoToEntity(userDto));
        return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.mapTokenToJwtAuthResponse(jwtToken));
    }


    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token) {
        try {
            String tokenValue = token.replace("Bearer ", "");
            String username = jwtService.extractUsername(tokenValue);
            Claims claims = jwtService.extractAllClaims(tokenValue);
            List<String> rolesList = claims.get("roles", List.class);
//            Set<String> roles = rolesList.stream().map(String::valueOf) .collect(Collectors.toSet());
            String email = claims.get("email", String.class);
            UserDto userDto = UserDto.builder()
                    .id(1)
                    .email(email)
                    .username(username)
                    .build();

            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/me")
    public Set<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return roles;
    }
}