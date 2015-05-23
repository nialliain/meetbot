package niall.receiver;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import niall.domain.Meeting;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IcalHelperTest {

    @Test
    public void testDecode() throws IOException {
        List<Meeting> decodedMeetings = IcalHelper.decodeIcal(Resources.toString(Resources.getResource("TestMeeting.ics"), Charsets.UTF_8));
        List<Meeting> expectedMeetings = Arrays.asList(new Meeting("Test Meeting", "Campbell, Niall I", Arrays.asList("niall@wetdreams.org.uk"), LocalDateTime.parse("2015-05-21T09:00"), LocalDateTime.parse("2015-05-21T09:30")));
        assertEquals(expectedMeetings, decodedMeetings);
    }

}