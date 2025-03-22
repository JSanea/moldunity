package web.app.moldunity.entity.article;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.util.DateTimeUtil;

import java.time.LocalDateTime;


@Data
@MappedSuperclass
@EqualsAndHashCode
public abstract class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String offerType;

    @Column(length = 128)
    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String subcategory;

    @NotEmpty
    private String price;

    private Integer view;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    @NotEmpty
    private String phone;

    @Column(length = 1024)
    private String description;

//    private LocalDateTime createdAt;
//
//    private LocalDateTime updateAt;
//
//    private LocalDateTime republishedAt;
//
//    public void setDateTimeFields(){
//        LocalDateTime dt = DateTimeUtil.getDateTimeNow();
//        this.setCreatedAt(dt);
//        this.setUpdateAt(dt);
//        this.setRepublishedAt(dt);
//    }
}
