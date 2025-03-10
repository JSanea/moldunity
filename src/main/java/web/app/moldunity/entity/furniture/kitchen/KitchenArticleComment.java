package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleComment;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kitchen_comments")
public class KitchenArticleComment extends ArticleComment {
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenArticleCommentReply> replies;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private KitchenArticle article;
}
