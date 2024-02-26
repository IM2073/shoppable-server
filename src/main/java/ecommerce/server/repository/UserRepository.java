package ecommerce.server.repository;

import ecommerce.server.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    @Transactional
    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Transactional
    @Query(value = "INSERT INTO users (name, email, password, role) VALUES (:name, :email, :password, :role)", nativeQuery = true)
    void register(@Param("name") String name, @Param("email") String email, @Param("password") String password, @Param("role") String role);
}
