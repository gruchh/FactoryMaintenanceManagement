package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}