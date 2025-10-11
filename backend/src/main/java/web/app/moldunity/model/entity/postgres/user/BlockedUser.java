package web.app.moldunity.model.entity.postgres.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "blocked_users")
public class BlockedUser {
    @Id
    private Long id;
    @NotBlank private String reason;
    private Long userId;
    private LocalDateTime createdAt;
}
