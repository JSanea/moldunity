package web.app.moldunity.entity.postgres.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.r2dbc.spi.Row;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "users")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
    @Id
    private Long id;
    @NotBlank private String username;
    @NotBlank private String password;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid email")
    @NotBlank private String email;
    @NotBlank private String country;
    @NotBlank private String location;
    private String role;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoggedInAt;
    private static final User EMPTY_USER = new User();

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setDateTimeFields(){
        var t = LocalDateTime.now();
        this.createdAt = t;
        this.updatedAt = t;
    }

    public static User mapRowToUser(Row row){
        return User.builder()
                .id(row.get("id", Long.class))
                .username(row.get("username", String.class))
                .password(row.get("password", String.class))
                .email(row.get("email", String.class))
                .role(row.get("role", String.class))
                .country(row.get("country", String.class))
                .location(row.get("location", String.class))
                .phone(row.get("phone", String.class))
                .createdAt(row.get("created_at", LocalDateTime.class))
                .updatedAt(row.get("updated_at", LocalDateTime.class))
                .build();
    }

    public static User empty(){
        return EMPTY_USER;
    }
}





















