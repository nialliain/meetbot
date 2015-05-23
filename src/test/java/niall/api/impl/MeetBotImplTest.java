package niall.api.impl;

import niall.api.MeetBot;
import niall.dao.MeetingDao;
import niall.domain.Meeting;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:meetBotApplicationContext.xml")
public class MeetBotImplTest {

    @Autowired
    private MeetBot meetBot;

    @Autowired
    private MeetingDao meetingDao;

    @Test
    public void testAddMeeting(){
        Meeting meeting = new Meeting("Test Meeting", "Niall", Arrays.asList("Niall"), LocalDateTime.parse("2015-05-20T18:00"), LocalDateTime.parse("2015-05-20T18:30"));
        meetBot.addNewMeeting(meeting);
        Set<Meeting> meetingList = meetingDao.getAll();
        assertEquals(1,meetingList.size());
        assertEquals(meeting,meetingList.iterator().next());
    }


    @Test
    public void testAddNewMeeting() throws Exception {

    }
}