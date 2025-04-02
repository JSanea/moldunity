package web.app.moldunity.entity.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public abstract class View {
    @Id
    private String id;

    @Indexed(unique = true)
    private String viewId;

    @Indexed
    private Long articleId;

    public View(String viewId, Long articleId) {
        this.viewId = viewId;
        this.articleId = articleId;
    }
}
