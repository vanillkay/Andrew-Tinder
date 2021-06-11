package Tinder.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieHelper {
    public static Optional<Cookie> getCookie(HttpServletRequest req, String name){
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return Optional.empty();
        return Arrays.stream(req.getCookies()).filter(c -> c.getName().equals(name)).findFirst();
    }
}
