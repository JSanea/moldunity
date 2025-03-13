package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

import java.util.List;

@Data
@Entity
@Table(name = "kitchen_furniture_comments")
public class KitchenArticleComment extends FurnitureComment<KitchenArticle, KitchenArticleCommentReply> {

}
