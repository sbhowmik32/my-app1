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
public class AuthenticationWizardMain {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationWizardMain.class);
    public static void main(String[] args){
        SpringApplication app = new SpringApplication(AuthenticationWizardMain.class);
        ApplicationContext context = app.run(args);
        log.info("==============================================================");
        log.info("Authentication wizard started!");
        log.info("==============================================================");
    }
}
