package web.app.moldunity.entity.services;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class ServicesArticle extends Article {
}
