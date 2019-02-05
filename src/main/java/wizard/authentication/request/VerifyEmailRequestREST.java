package wizard.authentication.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class VerifyEmailRequestREST {
    @NotNull
    public Long accountId;
    @NotBlank
    public String verificationCode;
}
