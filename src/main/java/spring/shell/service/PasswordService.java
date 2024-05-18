package spring.shell.service;

import lombok.NonNull;

public interface PasswordService {
    String addPassword(@NonNull String username, @NonNull String newPassword, @NonNull String service);

    String getPassword(@NonNull String username, @NonNull String service);

    String createPasswordWithLength(@NonNull String username, @NonNull String password, @NonNull String service);

    String createStrongPassword(@NonNull String username, @NonNull String password, @NonNull String service);
}
