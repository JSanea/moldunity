package web.app.moldunity.entity.mysql.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "users")
public class User extends UserArticle{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 32)
    @Size(min = 3, max = 32)
    @NotEmpty
    private String username;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid email")
    @Column(unique = true)
    @NotEmpty
    private String email;

    @Column(length = 256)
    @Size(min = 8, max = 256)
    private String password;

    @NotEmpty
    private String role;

    @NotEmpty
    private String country;

    @NotEmpty
    private String location;

    @NotEmpty
    private String phone;

    private Boolean blocked;

    private LocalDate createdAt;

    private LocalDate lastLogonDate;

    public void setIgnoredFields(){
        this.password = null;
        this.email = null;
        this.role = null;
        this.blocked = null;
    }
}






















