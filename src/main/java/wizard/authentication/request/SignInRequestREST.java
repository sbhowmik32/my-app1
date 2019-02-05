package wizard.authentication.request;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class SignInRequestREST {
    @NotBlank
    @Email
    public String emailAddress;

    @NotBlank
    @Size(min = 6, max = 20)
    public String password;
}
