package web.app.moldunity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.util.DateTimeUtil;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String eId;

    @NotEmpty
    private String subcategory;

    @NotEmpty
    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime republishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void setDateTimeFields(){
        LocalDateTime dt = DateTimeUtil.getDateTimeNow();
        this.setCreatedAt(dt);
        this.setUpdateAt(dt);
        this.setRepublishedAt(dt);
    }
}






