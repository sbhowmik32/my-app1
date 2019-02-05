package wizard.authentication.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wizard.authentication.db.EmailVerification;
import wizard.authentication.db.User;
import wizard.authentication.exception.OperationNotAllowedException;
import wizard.authentication.exception.UnauthorizedException;
import wizard.authentication.exception.UserExistException;
import wizard.authentication.repo.EmailVerificationRepository;
import wizard.authentication.repo.UserRepository;
import wizard.authentication.request.SignUpRequestREST;

import javax.inject.Inject;
import java.util.UUID;

@Service
public class SignUpService {
    private static final Logger log = LoggerFactory.getLogger(SignUpService.class);

    private static final Integer SLOW_LOG_ROUNDS = 10;

    private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";

    @Value("${emailAddress.verificationlink}")
    private String emailVerificationLinkBase;

    @Inject
    UserRepository userRepository;

    @Inject
    EmailSenderService emailSenderService;

    @Inject
    EmailVerificationRepository emailVerificationRepository;

    @Transactional
    public void createAccount(SignUpRequestREST requestREST) {
        validateUserDoesNotExist(requestREST.emailAddress);

        String salt = BCrypt.gensalt(SLOW_LOG_ROUNDS);
        String saltedPassword = BCrypt.hashpw(requestREST.password, salt);

        User user = new User();
        user.email = requestREST.emailAddress;
        user.name = requestREST.name;
        user.password = saltedPassword;
        userRepository.save(user);
        sendEmailVerificationLink(user);
    }

    @Transactional
    public void verifyEmail(long accountId, String verificationCode) {
        EmailVerification emailVerification = emailVerificationRepository.findByAccountIdAndVerificationCode(accountId, verificationCode);
        if (emailVerification == null) {
            throw new UnauthorizedException("Email does not exist");
        }
        if (emailVerification.isActive.equals(Boolean.FALSE)) {
            throw new OperationNotAllowedException("Email ie already verified");
        }
        emailVerification.isActive = Boolean.FALSE;
        emailVerificationRepository.save(emailVerification);

        User user = userRepository.findOne(accountId);
        user.isEmailVerified = Boolean.TRUE;
        userRepository.save(user);
    }

    private void sendEmailVerificationLink(User user) {
        log.info("Generating and sending emailAddress verification link");
        String verificationCode = UUID.randomUUID().toString();
        String emailVerificationLink = emailVerificationLinkBase + "/" + user.accountId + "/" + verificationCode;
        log.info("Email verification link: {}", emailVerificationLink);
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.accountId = user.accountId;
        emailVerification.verificationCode = verificationCode;
        emailVerificationRepository.save(emailVerification);
        emailSenderService.sendEmail(user.email, EMAIL_VERIFICATION_SUBJECT, emailVerificationLink);
    }

    private void validateUserDoesNotExist(String emailAddress) {
        User user = userRepository.findByEmail(emailAddress);
        if (user != null) {
            log.info("User already exist!");
            throw new UserExistException();
        }
    }
}
