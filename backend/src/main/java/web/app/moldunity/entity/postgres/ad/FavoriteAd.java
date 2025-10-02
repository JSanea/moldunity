package web.app.moldunity.entity.postgres.ad;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(value = "favorite_ads")
public class FavoriteAd {
    @Id
    private Long id;
    @NotNull  private Long userId;
    @NotNull  private Long adId;
    @NotNull  private LocalDateTime createdAt;
}





