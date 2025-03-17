package web.app.moldunity.entity.immobile.home;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.immobile.ImmobileView;

@Data
@Entity
@Table(name = "home_views")
public class HomeView extends ImmobileView<Home> {
}
