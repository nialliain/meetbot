package niall.mail;

/**
 * Created by niall on 22/05/15.
 */
public interface MailSender {

    public void sendMail(String to, String subject, String body);

}
