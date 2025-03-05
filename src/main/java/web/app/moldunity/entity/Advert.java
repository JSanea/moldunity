package web.app.moldunity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String category;

    @NotEmpty
    private String subcategory;

    @NotEmpty
    private Integer price;

    @NotEmpty
    private String state;

    @NotEmpty
    private LocalDateTime createdAt;

    @NotEmpty
    private String country;

    @Column(length = 1024)
    @NotEmpty
    private String description;
}
