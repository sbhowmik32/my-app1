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
import wizard.authentication.async.SignInControllerAsync;
import wizard.authentication.request.CommonMessageResponseREST;
import wizard.authentication.request.SignInRequestREST;

import javax.inject.Inject;

@RestController
@RequestMapping("/signin")
public class SigninREST {
    private static Logger log = LoggerFactory.getLogger(SignUpREST.class);

    @Inject
    SignInControllerAsync signInControllerAsync;

    @ApiOperation(value = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CommonMessageResponseREST.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<CommonMessageResponseREST>> signin(
            @RequestBody SignInRequestREST requestREST
    ) {
        DeferredResult<ResponseEntity<CommonMessageResponseREST>> result = new DeferredResult<>();
        signInControllerAsync.signIn(result, requestREST);
        return result;
    }
}
