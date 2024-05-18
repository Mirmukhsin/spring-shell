package spring.shell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.shell.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    @Query("select u.url from Url u where u.description = ?1 and u.username = ?2")
    String getUrl(String description, String username);

    @Modifying
    @Transactional
    @Query("delete from Url u where u.description = ?1 and u.username = ?2")
    void deleteUrl(String description, String username);

    Url getByCode(String url);

    boolean existsUrlByDescriptionAndUsername(String description, String username);
}