package niall.mail;

import niall.domain.Meeting;

public class MailHelper {

    public static void sendSurveyMail(MailSender mailSender, Meeting meeting, String address){
        mailSender.sendMail(address, new StringBuilder("Please rate ").append(meeting.getOrganiser()).append("'s meeting!").toString(), new StringBuilder("How was ").append(meeting.getOrganiser()).append("'s meeting?").toString());
    }

    public static void sendResultsMail(MailSender mailSender, Meeting meeting) {
        mailSender.sendMail(meeting.getOrganiser(), "Here's your rating!", new StringBuilder("You had a ").append(meeting.getSuccess()).append(" success rate.").toString());
    }
}
