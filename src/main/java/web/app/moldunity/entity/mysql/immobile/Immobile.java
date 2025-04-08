package web.app.moldunity.entity.mysql.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Category;
import web.app.moldunity.entity.mysql.immobile.apartament.Apartament;
import web.app.moldunity.entity.mysql.immobile.home.Home;

import java.util.List;

@Data
@Entity
@Table(name = "immobile")
@EqualsAndHashCode(callSuper = true)
public class Immobile extends Category {
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private Apartament apartament;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private Home home;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ImmobileImage> immobileImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "favorite", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteImmobile> favoriteImmobiles;
}
