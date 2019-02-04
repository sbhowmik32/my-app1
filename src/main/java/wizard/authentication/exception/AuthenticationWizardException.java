package wizard.authentication.exception;

public class AuthenticationWizardException extends RuntimeException {

    public int statusCode;
    public String exceptionReason;

    public AuthenticationWizardException(String message) {
        super(message);
        exceptionReason = message;
    }

    public AuthenticationWizardException(String message, String exceptionReason) {
        super(message);
        this.exceptionReason = exceptionReason;
    }

}
