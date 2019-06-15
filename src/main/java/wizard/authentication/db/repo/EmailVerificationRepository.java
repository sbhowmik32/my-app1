package wizard.authentication.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wizard.authentication.db.pojo.EmailVerification;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    EmailVerification findByAccountIdAndVerificationCode(long accountId, String verificationCode);
}
