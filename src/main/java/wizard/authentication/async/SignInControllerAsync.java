package wizard.authentication.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.async.DeferredResult;
import wizard.authentication.request.CommonMessageResponseREST;
import wizard.authentication.request.SignInRequestREST;
import wizard.authentication.service.SignInService;
import wizard.authentication.utils.AuthenticationWizardSays;

import javax.inject.Inject;

@Controller
public class SignInControllerAsync {
    private static final Logger log = LoggerFactory.getLogger(SignUpControllerAsync.class);

    @Inject
    private SignInService signinService;

    @Async
    public void signIn(
            DeferredResult<ResponseEntity<CommonMessageResponseREST>> result,
            SignInRequestREST requestREST
    ) {
        log.info("Processing SIGN IN request");
        signinService.signIn(requestREST.emailAddress, requestREST.password);
        AuthenticationWizardSays.ok(result, "Signed in successfully!");
    }
}
