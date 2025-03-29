package web.app.moldunity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String price;

    private Integer view;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    @NotEmpty
    private String phone;
}






