package web.app.moldunity.entity.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.mysql.user.User;

@Data
@MappedSuperclass
public abstract class Favorite<A> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    @JsonIgnore
    private A favorite;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
