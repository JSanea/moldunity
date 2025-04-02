package web.app.moldunity.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "furniture_views")
public class FurnitureView extends View{
    public FurnitureView(String viewId, Long articleId) {
        super(viewId, articleId);
    }
}
