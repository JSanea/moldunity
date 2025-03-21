package web.app.moldunity.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.immobile.Immobile;

import java.util.List;
import java.util.Set;

@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class UserArticle {
    /************************** Furniture **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
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
//
    /************************** Immobile **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Immobile> immobiles;

}
























