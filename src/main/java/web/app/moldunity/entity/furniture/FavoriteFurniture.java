package web.app.moldunity.entity.furniture;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.Favorite;

@Data
@Entity
@Table(name = "favorite_furniture")
public class FavoriteFurniture extends Favorite<Furniture> {
}
