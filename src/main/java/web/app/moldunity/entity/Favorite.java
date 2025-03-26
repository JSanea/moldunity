package web.app.moldunity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.user.User;

@Data
@MappedSuperclass
public abstract class Favorite<A> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String subcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_id")
    @JsonIgnore
    private A favorite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
