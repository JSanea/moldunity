package web.app.moldunity.entity.immobile;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.Favorite;

@Data
@Entity
@Table(name = "favorite_immobile")
public class FavoriteImmobile extends Favorite<Immobile>{

}
