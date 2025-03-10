package web.app.moldunity.entity.article;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Column(length = 512)
    @NotEmpty
    private String txt;

    private LocalDateTime createdAt;
}
