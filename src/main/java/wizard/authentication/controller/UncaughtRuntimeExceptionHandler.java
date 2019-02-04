package wizard.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import wizard.authentication.exception.AuthenticationWizardException;
import wizard.authentication.request.CommonMessageResponseREST;

import java.lang.reflect.Method;

public class UncaughtRuntimeExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(UncaughtRuntimeExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable exception, Method method, Object... params) {
        if (params[0] instanceof DeferredResult) {
            DeferredResult<ResponseEntity<CommonMessageResponseREST>> errorResult
                    = (DeferredResult<ResponseEntity<CommonMessageResponseREST>>) params[0];
            processIpayException((AuthenticationWizardException) exception, errorResult);
        }
    }

    private void processIpayException(AuthenticationWizardException exception, DeferredResult<ResponseEntity<CommonMessageResponseREST>> errorResult) {
        log.info("Exception here: {}", exception);
        CommonMessageResponseREST errorResponseRest = new CommonMessageResponseREST();
        errorResponseRest.message = exception.getMessage();
        ResponseEntity<CommonMessageResponseREST> entity = new ResponseEntity<>(errorResponseRest, HttpStatus.valueOf(exception.statusCode));
        errorResult.setResult(entity);
    }
}
