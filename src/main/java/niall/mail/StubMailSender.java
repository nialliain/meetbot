package niall.mail;

/**
 * Created by niall on 22/05/15.
 */
public class StubMailSender implements MailSender {

    @Override
    public void sendMail(String to, String subject, String body) {
        System.out.println("Would send a mail to " + to + " about " + subject);

    }

}
