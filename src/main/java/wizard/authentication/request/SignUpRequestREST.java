package wizard.authentication.request;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequestREST {
    @NotNull
    @Email
    public String emailAddress;

    @NotNull
    public String name;

    @NotNull
    @Size(min = 6, max = 20)
    public String password;
}
