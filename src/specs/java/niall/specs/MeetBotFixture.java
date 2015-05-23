package niall.specs;

import niall.api.impl.MeetBotImpl;
import niall.domain.Meeting;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.concordion.integration.junit4.ConcordionRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

@RunWith(ConcordionRunner.class)
public class MeetBotFixture {

    MeetBotImpl meetBot;
    TestMailSender testMailSender;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:meetBotApplicationContext.xml");
        meetBot = (MeetBotImpl)applicationContext.getBean("MeetBot");
        testMailSender = new TestMailSender();
        meetBot.setMailSender(testMailSender);
    }

    public void addMeeting(String organiser, String participants, String subject, String from, String to){
        meetBot.addNewMeeting(new Meeting(subject, organiser, Arrays.asList(participants.split(", ")), LocalDateTime.parse(from), LocalDateTime.parse(to)));
    }

    public void checkForFinishedMeetings(){
        meetBot.mailParticipantsOfRecentlyExpiredMeetings();
    }

    public List<TestMailSender.Mail> getSentMails(){
        return testMailSender.getSentMails();
    }

    public void returnResponse(String participant, String response) throws Exception {
        meetBot.rateMeeting(1, "Good".equals(response));

    }

}
