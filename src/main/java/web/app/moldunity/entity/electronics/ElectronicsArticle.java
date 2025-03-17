package web.app.moldunity.entity.electronics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.user.User;

import java.util.List;

@Data
@MappedSuperclass
public abstract class ElectronicsArticle<I, V> extends Article {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String state;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<I> images;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<V> views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
