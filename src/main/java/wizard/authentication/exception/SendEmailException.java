package wizard.authentication.exception;

import org.apache.http.HttpStatus;

public class SendEmailException extends AuthenticationWizardException {
    public SendEmailException() {
        super("Mail sending failed");
        this.statusCode = HttpStatus.SC_NOT_ACCEPTABLE;
    }
}
