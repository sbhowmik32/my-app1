package wizard.authentication.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wizard.authentication.db.pojo.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String emailAddress);
}
