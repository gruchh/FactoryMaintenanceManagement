package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterDto {
    private String username;
    private String password;
    private String email;
}
