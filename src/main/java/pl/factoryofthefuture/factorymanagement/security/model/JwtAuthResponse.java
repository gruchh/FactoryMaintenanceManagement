package pl.factoryofthefuture.factorymanagement.security.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
