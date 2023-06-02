package dev.bastida.finca.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security")
@Getter
@Setter
public class SecurityConfigProperties {

    private JwtProperties jwt;

    public record JwtProperties(String secret, long expiration, long refreshTokenExpiration) {
    }
}
