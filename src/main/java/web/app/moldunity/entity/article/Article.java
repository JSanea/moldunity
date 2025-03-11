package web.app.moldunity.entity.article;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
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
