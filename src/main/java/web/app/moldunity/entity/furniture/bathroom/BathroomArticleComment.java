package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleComment;
import web.app.moldunity.entity.furniture.FurnitureComment;

import java.util.List;

@Data
@Entity
@Table(name = "bathroom_comments")
public class BathroomArticleComment extends FurnitureComment<BathroomArticle, BathroomArticleCommentReply> {

}
