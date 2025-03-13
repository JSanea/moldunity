package web.app.moldunity.entity.furniture.wardrobe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "wardrobe_comments")
public class WardrobeComment extends FurnitureComment<Wardrobe, WardrobeCommentReply> {
}
