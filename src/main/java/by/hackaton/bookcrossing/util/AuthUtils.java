package by.hackaton.bookcrossing.util;

import by.hackaton.bookcrossing.configuration.oauth.CustomOAuth2User;
import org.springframework.security.core.Authentication;

public class AuthUtils {

    public static String getEmailFromAuth(Authentication auth) {
        return ((CustomOAuth2User) auth.getPrincipal()).getEmail();
    }
}
