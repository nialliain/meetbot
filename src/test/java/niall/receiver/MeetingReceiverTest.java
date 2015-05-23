package niall.receiver;

import org.junit.Test;

import static org.junit.Assert.*;

public class MeetingReceiverTest {

    @Test
    public void testReceiveMeetings() throws Exception {
        MeetingReceiver meetingReceiver = new MeetingReceiver();
        meetingReceiver.receiveMeetings();

    }
}