package niall.specs;

import niall.mail.MailSender;

import java.util.ArrayList;
import java.util.List;

public class TestMailSender implements MailSender {

    private List<Mail> mails;

    public TestMailSender(){
        mails = new ArrayList<Mail>();
    }

    @Override
    public void sendMail(String to, String subject, String body) {
        mails.add(new Mail(to,subject,body));
    }

    public List<Mail> getSentMails(){
        List<Mail> sentMails = mails;
        mails = new ArrayList<Mail>();
        return sentMails;
    }

    public class Mail {

        private String to, subject, body;

        public Mail(String to, String subject, String body){
            this.to = to;
            this.subject = subject;
            this.body = body;
        }

        public String getTo() {
            return to;
        }

        public String getSubject() {
            return subject;
        }

        public String getBody() {
            return body;
        }
    }
}
