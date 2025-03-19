package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.Set;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Housing<I, V> extends Article {
    @NotEmpty
    private String area;

    @NotEmpty
    private String kitchenArea;

    @NotEmpty
    private String rooms;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<I> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<V> views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
