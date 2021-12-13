package vea.kuc0277.project.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
