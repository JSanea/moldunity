package web.app.moldunity.entity.article;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticleComment;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@MappedSuperclass
public abstract class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String subcategory;

    @NotEmpty
    private Integer price;

    @NotEmpty
    private String state;

    @NotEmpty
    private Integer view;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    @NotEmpty
    private String phone;

    @Column(length = 1024)
    @NotEmpty
    private String description;

    @NotEmpty
    private LocalDateTime createdAt;

    @NotEmpty
    private LocalDateTime updateAt;
}
