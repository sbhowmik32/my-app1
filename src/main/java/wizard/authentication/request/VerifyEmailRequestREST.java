package wizard.authentication.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VerifyEmailRequestREST {
    @NotNull
    @Min(1)
    public Long accountId;

    @NotBlank
    public String verificationCode;
}
