package web.app.moldunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.app.moldunity.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.username = ?1")
    Optional<User> findByName(String name);

    @Query(value = "select username from users where username = :username", nativeQuery = true)
    String existUsername(@Param("username") String username);

    @Query(value = "select email from users where email = :email", nativeQuery = true)
    String existEmail(@Param("email") String email);

    @Query(value = "select password from users where username = :username", nativeQuery = true)
    String getPasswordByUsername(@Param("username") String username);

    @Query(value = "select role from users where username = :username", nativeQuery = true)
    String getRoleByUsername(@Param("username") String username);

    @Query(value = "select password, role from users where username = :username", nativeQuery = true)
    List<String> getPasswordAndRoleByUsername(@Param("username") String username);
}
