package wizard.authentication.exception;


import org.apache.http.HttpStatus;

public class UserExistException extends AuthenticationWizardException {
    public UserExistException() {
        this("User already exist!");
        statusCode = HttpStatus.SC_NOT_ACCEPTABLE;
    }

    public UserExistException(String message) {
        super(message);
        statusCode = HttpStatus.SC_NOT_ACCEPTABLE;
    }
}
