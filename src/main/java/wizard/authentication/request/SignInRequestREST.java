package wizard.authentication.request;


import org.hibernate.validator.constraints.NotBlank;

public class SignInRequestREST {
    @NotBlank
    public String emailAddress;

    @NotBlank
    public String password;
}
