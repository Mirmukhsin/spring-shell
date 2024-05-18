package spring.shell.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.shell.entity.Url;
import spring.shell.repository.UrlRepository;
import spring.shell.utils.BaseUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository repository;
    private final BaseUtils utils;

    @Override
    public String shortenUrl(@NonNull String username, @NonNull String url, @NonNull String description) {
        String urlCode = utils.shortenUrlCode(username);
        Url shortenUrl = Url.builder()
                .url(url)
                .code(urlCode)
                .description(description)
                .username(username)
                .build();
        repository.save(shortenUrl);
        return "Successfully! Your shorten url http://localhost:8080/panda/%s".formatted(urlCode);
    }

    @Override
    public String getUrl(@NonNull String username, @NonNull String description) {
        return Objects.requireNonNullElse(repository.getUrl(description, username), "Url Not Found");
    }

    @Override
    public String deleteUrl(@NonNull String description, @NonNull String username) {
        if (repository.existsUrlByDescriptionAndUsername(description, username)) {
            repository.deleteUrl(description, username);
            return "Url successfully deleted!";
        } else {
            return "Url Not Found";
        }
    }

    @Override
    public Url getByCode(@NonNull String code) {
        return repository.getByCode(code);
    }

}
