package niall.dao;

import niall.domain.Meeting;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

public class MeetingDaoTest {

    @Test
    public void test() throws Exception {
        MeetingDao meetingDao = new MeetingDao();
        Meeting meeting1 = new Meeting("Test Meeting 1", "Niall", Arrays.asList("Niall"), LocalDateTime.parse("2015-05-20T18:00"), LocalDateTime.parse("2015-05-20T18:30"));
        Meeting meeting2 = new Meeting("Test Meeting 2", "Niall", Arrays.asList("Niall"), LocalDateTime.parse("2015-05-20T18:00"), LocalDateTime.parse("2020-05-20T18:30"));
        meetingDao.add(meeting1);
        meetingDao.add(meeting2);
        Set<Meeting> meetingList = meetingDao.getAll();
        assertEquals(2, meetingList.size());
        meetingList = meetingDao.getPendingEnd();
        assertEquals(1, meetingList.size());
        assertEquals(meeting1, meetingList.iterator().next());


    }

}