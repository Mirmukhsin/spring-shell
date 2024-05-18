package spring.shell.utils;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class BaseUtils {
    public String shortenUrlCode(String username) {
        return Hashing
                .murmur3_32_fixed()
                .hashString(UUID.randomUUID().toString() + username, StandardCharsets.UTF_8)
                .toString();
    }
}
