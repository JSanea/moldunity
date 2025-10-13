package web.app.moldunity.service.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.event.UpdateRefreshTokenEvent;
import web.app.moldunity.model.entity.postgres.security.RefreshToken;
import web.app.moldunity.service.data.ReactiveDataManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final ReactiveDataManager dataManager;

    public Mono<Long> update(String token, Long userId){
        return dataManager.entityTemplate().exists(Query.query(Criteria.where("user_id").is(userId)), RefreshToken.class)
            .flatMap(exists -> {
                if (exists){
                    Map<SqlIdentifier, Object> assignments = new HashMap<>();
                    assignments.put(SqlIdentifier.unquoted("token"), token);
                    assignments.put(SqlIdentifier.unquoted("updated_at"), LocalDateTime.now());
                    return dataManager.entityTemplate()
                            .update(
                                    Query.query(Criteria.where("user_id").is(userId)),
                                    Update.from(assignments),
                                    RefreshToken.class
                            );
                }else {
                    var r = new RefreshToken(token, userId);
                    r.setUpdatedAt(LocalDateTime.now());
                    return dataManager.entityTemplate()
                            .insert(r)
                            .map(RefreshToken::getId);
                }
            })
            .as(dataManager.txOperator()::transactional)
            .onErrorResume(e -> {
                log.error("Error to update refresh token: {}", e.getMessage(), e);
                return Mono.error(new RuntimeException("Error to update refresh token"));
            });
    }

    @EventListener
    public Mono<Long> updateEvent(UpdateRefreshTokenEvent event){
        return update(event.token(), event.userId());
    }
}
