package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Category;
import web.app.moldunity.entity.immobile.apartament.Apartament;
import web.app.moldunity.entity.immobile.home.Home;

import java.util.List;

@Data
@Entity
@Table(name = "immobile")
@EqualsAndHashCode(callSuper = true)
public class Immobile extends Category {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Apartament> apartaments;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Home> homes;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "immobile", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ImmobileImage> immobileImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "favorite", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteImmobile> favoriteImmobiles;
}
