package wizard.authentication.db.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "email_verification")
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @NotNull
    @Column(name = "account_id")
    public Long accountId;

    @NotNull
    @Column(name = "verification_code")
    public String verificationCode;

    @NotNull
    @Column(name = "is_active")
    public Boolean isActive = Boolean.TRUE;

    @NotNull
    @Column(name = "created_at")
    public Long createdAt = System.currentTimeMillis();

    @NotNull
    @Column(name = "updated_at")
    public Long updatedAt = System.currentTimeMillis();
}
