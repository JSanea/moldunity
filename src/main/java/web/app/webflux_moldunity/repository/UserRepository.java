package web.app.webflux_moldunity.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import web.app.webflux_moldunity.entity.postgres.user.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    @Query(value = "select * from users where username = :username")
    Mono<User> findByUsername(@Param("username") String username);

    @Query(value = "select username from users where username = :username")
    Mono<String> existUsername(@Param("username") String username);
}
