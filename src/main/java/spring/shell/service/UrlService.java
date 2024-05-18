package spring.shell.service;

import lombok.NonNull;
import spring.shell.entity.Url;

public interface UrlService {
    String shortenUrl(@NonNull String username, @NonNull String url, @NonNull String description);

    String getUrl(@NonNull String username, @NonNull String description);

    String deleteUrl(@NonNull String description, @NonNull String username);

    Url getByCode(@NonNull String code);
}
