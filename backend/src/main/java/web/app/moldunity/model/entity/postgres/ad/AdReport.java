package web.app.moldunity.model.entity.postgres.ad;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "reports")
public class AdReport {
    @Id
    private Long id;
    @NotBlank private String reason;
    private Long userId;
    private Long adId;
    private LocalDateTime createdAt;
}
