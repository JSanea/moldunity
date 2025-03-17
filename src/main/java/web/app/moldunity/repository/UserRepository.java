package web.app.moldunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.app.moldunity.entity.user.User;

import java.util.List;
import java.util.Optional;

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
    String selectPasswordByUsername(@Param("username") String username);

    @Query(value = "select role from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    String selectRoleByUsername(@Param("username") String username);

    @Query(value = "select password, role from users where username = :username", nativeQuery = true)
    @Transactional(readOnly = true)
    List<String> selectPasswordAndRoleByUsername(@Param("username") String username);

    @Query(value = "select u.id from User u where u.username = ?1")
    @Transactional(readOnly = true)
    Long selectIdByName(String username);
}
