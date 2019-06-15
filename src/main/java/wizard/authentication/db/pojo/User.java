package wizard.authentication.db.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public Long accountId;

    @NotNull
    @Column(name = "email")
    public String email;

    @NotNull
    @Column(name = "name")
    public String name;

    @NotNull
    @Column(name = "password")
    public String password;

    @NotNull
    @Column(name = "is_email_verified")
    public Boolean isEmailVerified = Boolean.FALSE;

    @NotNull
    @Column(name = "created_at")
    public Long createdAt = System.currentTimeMillis();

    @NotNull
    @Column(name = "updated_at")
    public Long updatedAt = System.currentTimeMillis();
}
