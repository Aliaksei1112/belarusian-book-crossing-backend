package by.hackaton.bookcrossing.dto.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {

    private final Auth auth = new Auth();

    @Getter
    @Setter
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
    }
}
