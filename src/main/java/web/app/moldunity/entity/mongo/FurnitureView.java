package web.app.moldunity.entity.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "furniture_views")
public class FurnitureView {
    @Id
    private String id;
    @Indexed(unique = true)
    private String viewId;
    @Indexed
    private Long furnitureId;

    public FurnitureView(String viewId, Long furnitureId) {
        this.viewId = viewId;
        this.furnitureId = furnitureId;
    }
}
