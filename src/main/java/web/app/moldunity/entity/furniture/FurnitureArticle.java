package web.app.moldunity.entity.furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.List;
import java.util.Set;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class FurnitureArticle<I, V> extends Article {
    @NotEmpty
    private String state;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<I> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private Set<V> views;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
