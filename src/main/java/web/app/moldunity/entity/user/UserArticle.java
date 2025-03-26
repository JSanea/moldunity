package web.app.moldunity.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import web.app.moldunity.entity.furniture.FavoriteFurniture;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.immobile.FavoriteImmobile;
import web.app.moldunity.entity.immobile.Immobile;

import java.util.List;

@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class UserArticle {
    /************************** Furniture **************************/
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Furniture> furnitures;

//    /************************** Appliances **************************/
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private Set<Appliances> appliances;
//
//    /************************** Electronics **************************/
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private Set<Electronics> electronics;
//
//    /************************** Transport **************************/
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private Set<Transport> transports;

    /************************** Immobile **************************/
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Immobile> immobiles;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteImmobile> favoriteImmobiles;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteFurniture> favoriteFurnitures;
}
























