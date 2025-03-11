package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.furniture.FurnitureArticle;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Data
@Entity
@Table(name = "bathroom")
public class BathroomArticle extends FurnitureArticle<BathroomArticleComment, BathroomArticleImage, BathroomArticleView> {

}
