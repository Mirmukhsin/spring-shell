package spring.shell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.shell.entity.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Integer> {
    @Query("select p.password from Password p where p.serviceName = ?1 and p.username = ?2")
    String getPassword(String serviceName, String username);
}