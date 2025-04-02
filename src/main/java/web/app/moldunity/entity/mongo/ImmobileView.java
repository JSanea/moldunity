package web.app.moldunity.entity.mongo;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "immobile_views")
public class ImmobileView extends View {
    public ImmobileView(String viewId, Long articleId) {
        super(viewId, articleId);
    }
}
