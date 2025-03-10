package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bathroom")
public class BathroomArticle extends Article {
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomArticleComment> comments;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomArticleImage> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomArticleView> views;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
