package web.app.moldunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select username from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    String existUsername(@Param("username") String username);

    @Query(value = "select email from users where email = :email", nativeQuery = true)
    @Transactional(readOnly = true)
    String existEmail(@Param("email") String email);

    @Query(value = "select password from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    String getPasswordByUsername(@Param("username") String username);

    @Query(value = "select role from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    String getRoleByUsername(@Param("username") String username);

    @Query(value = "select password, role from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    List<String> getPasswordAndRoleByUsername(@Param("username") String username);

    @Query(value = "select u.id from User u where u.username = ?1")
    @Transactional(readOnly = true)
    Long getIdByName(String username);

    @Modifying
    @Query(value = "UPDATE users SET password = :password WHERE email = :email", nativeQuery = true)
    @Transactional
    void updatePassword(@Param("email") String email, @Param("password") String password);
}






