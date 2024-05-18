package spring.shell.service;

import lombok.NonNull;

public interface UserService {
    String register(@NonNull String username,@NonNull String password);

    boolean login(@NonNull String username,@NonNull String password);
}
