package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Category;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.immobile.apartament.Apartament;
import web.app.moldunity.entity.immobile.home.Home;
import web.app.moldunity.entity.user.User;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "immobile")
public class Immobile extends Category {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Apartament> apartaments;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Home> homes;
}
