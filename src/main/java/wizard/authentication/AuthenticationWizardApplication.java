package wizard.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "wizard.authentication")
@EntityScan(basePackages = "wizard.authentication.db")
public class AuthenticationWizardApplication {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationWizardApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AuthenticationWizardApplication.class);
        ApplicationContext context = app.run(args);
        log.info("==============================================================");
        log.info("Authentication wizard started!");
        log.info("==============================================================");
    }
}
