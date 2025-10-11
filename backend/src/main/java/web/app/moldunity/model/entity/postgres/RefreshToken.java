package web.app.moldunity.model.entity.postgres;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(value = "refresh_tokens")
public class RefreshToken {
    @Id
    private Long id;
    @NotBlank String token;
    private Long userId;
    private LocalDateTime createdAt;
}
