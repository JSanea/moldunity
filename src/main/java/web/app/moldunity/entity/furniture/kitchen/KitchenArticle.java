package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kitchen")
public class KitchenArticle extends Article {
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenArticleComment> comments;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenArticleImage> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenArticleImage> views;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
