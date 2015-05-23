package niall.api;

import niall.domain.Meeting;

public interface MeetBot {

    public void addNewMeeting(Meeting meeting);
    public void mailParticipantsOfRecentlyExpiredMeetings();
    public void rateMeeting(long meetingId, boolean meetingWasGood) throws Exception;

}
