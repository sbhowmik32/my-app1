package wizard.authentication.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import wizard.authentication.exception.SendEmailException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@EnableAsync
@Service
public class EmailSenderService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.fromaddress}")
    private String fromAddress;


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


//    @Async
//    public CompletableFuture sendEmail(String recipientEmailAddress, String emailSubject, String emailText,) {
//        log.debug("Send email to " + recipientEmailAddress);
//        return sendEmail(recipientEmailAddress, emailSubject, emailText);
//    }

    @Async
    public CompletableFuture<String> sendEmail(String recipientEmailAddress, String emailSubject, String emailText) {

        log.debug("Sending email to " + recipientEmailAddress + " : " + emailSubject);

        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail, true);
            mimeMessageHelper.setFrom(new InternetAddress(fromAddress, "test-system"));
            mimeMessageHelper.setTo(recipientEmailAddress);
            mimeMessageHelper.setSubject(emailSubject);
            mimeMessageHelper.setText(
                    "test",
                    true);

            javaMailSender.send(mail);

        } catch (MessagingException | MailException | ResourceNotFoundException | UnsupportedEncodingException e) {
            log.error("Mail Sending failed", e);
            throw new SendEmailException();
        }
        return CompletableFuture.completedFuture("");
    }
}

