package web.app.moldunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.app.moldunity.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select username from users where username = :username", nativeQuery = true)
    String existUsername(@Param("username") String username);

    @Query(value = "select email from users where email = :email", nativeQuery = true)
    String existEmail(@Param("email") String email);
}
