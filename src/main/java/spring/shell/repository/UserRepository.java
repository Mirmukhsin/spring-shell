package spring.shell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shell.entity.AuthUser;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Integer> {
    boolean existsByUsername(String username);
    AuthUser findByUsername(String username);
}
