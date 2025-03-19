package web.app.moldunity.entity.transport;

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
public abstract class Auto<I, V> extends Article {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotEmpty
    private String year;

    @NotEmpty
    private String mileage;

    @NotEmpty
    private String combustible;

    @NotEmpty
    private String engine;

    @NotEmpty
    private String power;

    @NotEmpty
    private String gearBox;

    @NotEmpty
    private String side;

    @NotEmpty
    private String state;

    @NotEmpty
    private String color;

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














