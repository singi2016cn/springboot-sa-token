package top.singi.demo.util;

import cn.dev33.satoken.secure.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtil {
    public Boolean check(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String encrypt(String password) {
        return BCrypt.hashpw(password);
    }
}
