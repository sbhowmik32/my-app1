package wizard.authentication.rest;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import wizard.authentication.async.SignUpControllerAsync;
import wizard.authentication.request.CommonMessageResponseREST;
import wizard.authentication.request.SignUpRequestREST;
import wizard.authentication.request.VerifyEmailRequestREST;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("")
public class SignUpREST {

    private static Logger log = LoggerFactory.getLogger(SignUpREST.class);

    @Inject
    SignUpControllerAsync signUpControllerAsync;

    @ApiOperation(value = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CommonMessageResponseREST.class)
    })
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<CommonMessageResponseREST>> createNewAccount(
            @Valid @RequestBody SignUpRequestREST requestREST
    ) {
        DeferredResult<ResponseEntity<CommonMessageResponseREST>> result = new DeferredResult<>();
        signUpControllerAsync.createNewAccount(result, requestREST);
        return result;
    }

    @ApiOperation(value = "Verify user emailAddress")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CommonMessageResponseREST.class)
    })
    @RequestMapping(value = "/email/verification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<CommonMessageResponseREST>> verifyEmailAddress(
            @Valid @RequestBody VerifyEmailRequestREST requestREST
    ) {
        DeferredResult<ResponseEntity<CommonMessageResponseREST>> result = new DeferredResult<>();
        signUpControllerAsync.verifyEmail(result, requestREST);
        return result;
    }
}