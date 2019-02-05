package wizard.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wizard.authentication.db.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String emailAddress);
}
