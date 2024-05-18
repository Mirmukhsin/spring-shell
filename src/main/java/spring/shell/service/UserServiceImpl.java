package spring.shell.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.shell.entity.AuthUser;
import spring.shell.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String register(@NonNull String username, @NonNull String password) {
        if (!userRepository.existsByUsername(username)) {
            AuthUser authUser = AuthUser.builder()
                    .username(username)
                    .password(password)
                    .build();
            userRepository.save(authUser);
            return "User successfully registered!";
        } else {
            return "This username already exist!";
        }
    }

    @Override
    public boolean login(@NonNull String username, @NonNull String password) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
