package wizard.authentication.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wizard.authentication.db.User;
import wizard.authentication.exception.UserExistException;
import wizard.authentication.repo.UserRepository;
import wizard.authentication.request.SignUpRequestREST;

import javax.inject.Inject;

@Service
public class SignUpService {
    private static final Logger log = LoggerFactory.getLogger(SignUpService.class);

    private static final Integer SLOW_LOG_ROUNDS = 10;

    @Inject
    private UserRepository userRepository;


//    @Autowired
//    public SignUpService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

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
    }

    private void validateUserDoesNotExist(String emailAddress) {
//        User user = userRepository.findByEmailAddress(emailAddress);
//        if (user != null) {
//            throw new UserExistException();
//        }
    }
}
