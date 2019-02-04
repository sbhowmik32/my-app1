package wizard.authentication.db;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public Long accountId;

//    @NotNull
    @Column(name = "email")
    public String email;

//    @NotNull
    @Column(name = "name")
    public String name;

//    @NotNull
    @Column(name = "password")
    public String password;
}
