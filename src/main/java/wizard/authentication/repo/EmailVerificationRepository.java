package wizard.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wizard.authentication.db.EmailVerification;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    EmailVerification findByAccountIdAndVerificationCode(long accountId, String verificationCode);
}
