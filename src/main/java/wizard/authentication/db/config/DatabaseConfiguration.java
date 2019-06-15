package wizard.authentication.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableJpaRepositories(basePackages = "wizard.authentication.db.repo")
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    private Environment env;

    private static final String DATASOURCE_CLASS_NAME = "dataSourceClassName";
    private static final String PROPERTY_DATABASE_NAME = "databaseName";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_SERVER_NAME = "serverName";
    private static final String PROPERTY_USER_NAME = "username";
    private static final String PROPERTY_PASSWORD_NAME = "password";
    private static final String PROPERTY_PORT = "port";
    private static final String MAX_POOL_SIZE = "maximumPoolSize";

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        log.debug("Configuring Datasource...");
        databaseConnectionPoolCheck();
        HikariConfig config = configureDataSource();

        log.debug("Using database: " + config.getDataSourceProperties().get(PROPERTY_SERVER_NAME) + ":" + config.getDataSourceProperties().get(PROPERTY_DATABASE_NAME));
        return new HikariDataSource(config);
    }

    private HikariConfig configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(propertyResolver.getProperty(DATASOURCE_CLASS_NAME));
        addDatabaseServer(config);
        config.addDataSourceProperty("user", propertyResolver.getProperty(PROPERTY_USER_NAME));
        config.addDataSourceProperty("password", propertyResolver.getProperty(PROPERTY_PASSWORD_NAME));
        config.addDataSourceProperty("portNumber", propertyResolver.getProperty(PROPERTY_PORT));
        config.setMaximumPoolSize(propertyResolver.getProperty(MAX_POOL_SIZE, Integer.class));
        return config;
    }

    private void databaseConnectionPoolCheck() {
        if (propertyResolver.getProperty(PROPERTY_URL) == null
                && propertyResolver.getProperty(PROPERTY_DATABASE_NAME) == null) {
            log.error(
                    "Your database connection pool configuration is incorrect! The application"
                            + "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
    }

    private void addDatabaseServer(HikariConfig config) {
        if (propertyResolver.getProperty(PROPERTY_URL) == null
                || "".equals(propertyResolver.getProperty(PROPERTY_URL))) {
            config.addDataSourceProperty(PROPERTY_DATABASE_NAME, propertyResolver.getProperty(PROPERTY_DATABASE_NAME));
            config.addDataSourceProperty(PROPERTY_SERVER_NAME, propertyResolver.getProperty(PROPERTY_SERVER_NAME));
        } else {
            config.addDataSourceProperty(PROPERTY_URL, propertyResolver.getProperty(PROPERTY_URL));
        }
    }

}
