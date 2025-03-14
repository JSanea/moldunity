package web.app.moldunity.entity.furniture;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Data
@MappedSuperclass
public abstract class FurnitureArticle<I, V> extends Article {
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<I> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<V> views;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
