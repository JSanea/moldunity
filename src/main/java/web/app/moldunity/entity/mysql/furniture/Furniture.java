package web.app.moldunity.entity.mysql.furniture;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Category;

import java.util.List;


@Data
@Entity
@Table(name = "furniture")
@EqualsAndHashCode(callSuper = true)
public class Furniture extends Category {
    @NotEmpty
    private String price;

    private Integer view;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    @Column(unique = true)
    @NotEmpty
    private String phone;

    @NotEmpty
    private String state;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FurnitureImage> furnitureImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "favorite", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteFurniture> favoriteFurnitures;
}
