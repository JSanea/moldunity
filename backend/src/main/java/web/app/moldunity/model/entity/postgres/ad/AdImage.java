package web.app.moldunity.model.entity.postgres.ad;


import io.r2dbc.spi.Row;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table(value = "ad_images")
public class AdImage {
    @Id
    private Long id;
    @NotEmpty private String url;
    @NotNull  private LocalDateTime createdAt;
    @NotNull  private Long adId;

    public static AdImage mapRowToAdImage(Row row){
        return AdImage.builder()
                .id(row.get("ad_images_id", Long.class))
                .url(row.get("ad_images_url", String.class))
                .createdAt(row.get("ad_images_created_at", LocalDateTime.class))
                .adId(row.get("ad_images_ad_id", Long.class))
                .build();
    }

    @Override
    public String toString(){
        return "AdImage{id = " + id + ", url = " + url + ", createdAt = " + createdAt + ", adId = " + adId + '}';
    }
}






