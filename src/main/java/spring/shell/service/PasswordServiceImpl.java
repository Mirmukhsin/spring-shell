package spring.shell.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.shell.entity.Password;
import spring.shell.repository.PasswordRepository;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    private final PasswordRepository repository;

    @Override
    public String addPassword(@NonNull String username, @NonNull String newPassword, @NonNull String service) {
        Password password = Password.builder()
                .serviceName(service)
                .password(newPassword)
                .username(username)
                .build();
        repository.save(password);
        return "New Password Successfully added";
    }

    @Override
    public String getPassword(@NonNull String username, @NonNull String service) {
        return repository.getPassword(service, username);
    }

    @Override
    public String createPasswordWithLength(@NonNull String username, @NonNull String newPassword, @NonNull String service) {
        String generatedPsd = "00" + newPassword + "00";
        Password password = Password.builder()
                .username(username)
                .serviceName(service)
                .password(generatedPsd)
                .build();
        repository.save(password);
        return "New password created! Your new password %s for %s".formatted(generatedPsd, service);
    }

    @Override
    public String createStrongPassword(@NonNull String username, @NonNull String newPassword, @NonNull String service) {
        String generatedPsd = "9$" + newPassword + "$9";
        Password password = Password.builder()
                .username(username)
                .serviceName(service)
                .password(generatedPsd)
                .build();
        repository.save(password);
        return "New strong password created! Your new password %s for %s".formatted(generatedPsd, service);
    }

}
