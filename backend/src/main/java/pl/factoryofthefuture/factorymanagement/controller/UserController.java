package pl.factoryofthefuture.factorymanagement.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.factoryofthefuture.factorymanagement.entity.dto.UserDto;
import pl.factoryofthefuture.factorymanagement.mapper.UserDtoMapper;
import pl.factoryofthefuture.factorymanagement.security.model.JwtAuthResponse;
import pl.factoryofthefuture.factorymanagement.security.service.JwtService;
import pl.factoryofthefuture.factorymanagement.service.UserService;

import java.util.List;
import java.util.Map;
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
        try {
            String jwtToken = userService.verify(userDtoMapper.mapUserDtoToEntity(userDto));
            return ResponseEntity.ok(userDtoMapper.mapTokenToJwtAuthResponse(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        try {
            String tokenValue = token.replace("Bearer ", "");
            String username = jwtService.extractUsername(tokenValue);
            Claims claims = jwtService.extractAllClaims(tokenValue);
            List<Map<String, Object>> rolesList = claims.get("roles", List.class);
            Set<String> roles = rolesList.stream()
                    .map(role -> role.get("name").toString())
                    .collect(Collectors.toSet());
            String email = claims.get("email", String.class);
            UserDto userDto = UserDto.builder()
                    .email(email)
                    .username(username)
                    .roles(roles)
                    .build();
            return ResponseEntity.ok(userDto);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Set<String>> getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Set.of("ERROR_ROLE"));
        }

    }
}