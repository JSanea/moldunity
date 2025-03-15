package web.app.moldunity.entity.services;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.Article;

@Data
@MappedSuperclass
public abstract class ServicesArticle extends Article {
}
