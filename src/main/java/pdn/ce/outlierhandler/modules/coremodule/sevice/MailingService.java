package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.Email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailingService {
    private static String SMTP_HOST_NAME = "smtp.gmail.com";
    private static int SMTP_HOST_PORT = 465;
    private static String SMTP_AUTH_USER = "ppafyp@gmail.com";
    private static String SMTP_AUTH_PWD  = "ppafyp123";

    public static void main(String[] args) throws Exception{
        Email email = new Email();
        email.setBody("this is mail body");
        email.setSubject("Testing mail");
        email.setTo("okprabhath@gmail.com");
        new MailingService().send(email);
    }

    public void setSmtpAuthUser(String smtpAuthUser) {
        SMTP_AUTH_USER = smtpAuthUser;
    }

    public void setSmtpAuthPwd(String smtpAuthPwd) {
        SMTP_AUTH_PWD = smtpAuthPwd;
    }

    public void send(Email email) throws Exception{

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();

        String file = email.getAttachment();
        String fileName = "result.csv";
        DataSource source = new FileDataSource(file);

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(email.getSubject());

        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

//        message.setContent(email.getBody(), "text/html");
        message.setContent(multipart);

        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(email.getTo()));

        transport.connect
                (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}
