package web.app.moldunity.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticle;
import web.app.moldunity.entity.furniture.bedroom.BedroomArticle;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends UserArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 16)
    @NotEmpty
    private String username;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid email")
    @Column(unique = true)
    @NotEmpty
    private String email;

    @Column(length = 256)
    private String password;

    @NotEmpty
    private String role;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    private LocalDate createdAt;

    private LocalDate lastLogonDate;

//    /************************** Furniture **************************/
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<KitchenArticle> kitchenArticles;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<BathroomArticle> bathroomArticles;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<BedroomArticle> bedroomArticles;
}






















