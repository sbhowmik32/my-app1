package wizard.authentication.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import wizard.authentication.request.CommonMessageResponseREST;

public class AuthenticationWizardSays {

    private static Logger log = LoggerFactory.getLogger(AuthenticationWizardSays.class);


    public static void prepareSuccessResponse(String statusMessage, CommonMessageResponseREST message) {
        message.message = statusMessage;
        log.info("auth-wizard says: " + statusMessage);
    }

    public static void ok(DeferredResult<ResponseEntity<CommonMessageResponseREST>> result,
                          CommonMessageResponseREST message, String responseMessage) {
        prepareSuccessResponse(responseMessage, message);
        ResponseEntity<CommonMessageResponseREST> response = new ResponseEntity<>(message, HttpStatus.OK);
        result.setResult(response);
    }

    public static void ok(DeferredResult<ResponseEntity<CommonMessageResponseREST>> result) {
        ok(result, "Operation Successful");
    }

    public static void ok(DeferredResult<ResponseEntity<CommonMessageResponseREST>> result, String responseMessage) {
        ok(result, new CommonMessageResponseREST(), responseMessage);
    }
}
