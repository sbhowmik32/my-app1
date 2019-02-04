package wizard.authentication.configuration;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	private String protocol;
	private String host;
	private int port;
	private boolean auth;
	private boolean starttls;
	private String username;
	private String password;

	@Override
	public void setEnvironment(Environment env) {
		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.mail.");

	}

	private void initProperties() {
		protocol = propertyResolver.getProperty("protocol");
		host = propertyResolver.getProperty("host");
		port = Integer.parseInt(propertyResolver.getProperty("port"));
		auth = Boolean.parseBoolean(propertyResolver.getProperty("smtp.auth"));
		starttls = Boolean.parseBoolean(propertyResolver.getProperty("smtp.starttls.enable"));
		username = propertyResolver.getProperty("username");
		password = propertyResolver.getProperty("password");

	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		initProperties();
		Properties mailProperties = new Properties();
		// TODO currently ssl trust is set to any. should find a better solution
		mailProperties.put("mail.smtp.ssl.trust", "*");
		mailProperties.put("mail.smtp.auth", auth);
		mailProperties.put("mail.smtp.starttls.enable", starttls);

		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		return mailSender;
	}
	@Bean
	public VelocityEngine velocityEngine(){
		Properties velocityProperties = new Properties();
		velocityProperties.put("resource.loader", "class");
		velocityProperties.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		return new VelocityEngine(velocityProperties);
	}
}
