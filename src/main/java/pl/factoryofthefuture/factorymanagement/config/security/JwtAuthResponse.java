package pl.factoryofthefuture.factorymanagement.config.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
