package wizard.authentication.exception;

import org.apache.http.HttpStatus;

public class UnauthorizedException extends AuthenticationWizardException {
    public UnauthorizedException(){
        this("Unauthorized");
    }
    public UnauthorizedException(String message){
        super(message);
        statusCode = HttpStatus.SC_UNAUTHORIZED;
    }
}
