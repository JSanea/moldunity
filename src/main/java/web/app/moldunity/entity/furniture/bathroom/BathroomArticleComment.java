package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleComment;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bathroom_comments")
public class BathroomArticleComment extends ArticleComment {
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomArticleCommentReply> replies;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private BathroomArticle article;
}
