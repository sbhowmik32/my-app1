package wizard.authentication.exception;

import org.apache.http.HttpStatus;

public class OperationNotAllowedException extends AuthenticationWizardException {
    public OperationNotAllowedException(){
        this("Operation not allowed");
    }
    public OperationNotAllowedException(String message){
        super(message);
        statusCode = HttpStatus.SC_NOT_ACCEPTABLE;
    }
}
