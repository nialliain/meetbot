package niall.api.impl;

import niall.api.MeetBot;
import niall.dao.MeetingDao;
import niall.domain.Meeting;
import niall.mail.MailHelper;
import niall.mail.MailSender;

import java.util.Set;

public class MeetBotImpl implements MeetBot {

    private MeetingDao meetingDao;
    private MailSender mailSender;

    public void setMeetingDao(MeetingDao meetingDao) {
        this.meetingDao = meetingDao;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        meetingDao.add(meeting);
    }

    @Override
    public void mailParticipantsOfRecentlyExpiredMeetings() {
        Set<Meeting> recentlyFinishedMeetings = meetingDao.getPendingEnd();
        for(Meeting meeting : recentlyFinishedMeetings){
            for(String participant : meeting.getParticipants()){
                MailHelper.sendSurveyMail(mailSender, meeting, participant);
            }
        }
    }

    @Override
    public void rateMeeting(long meetingId, boolean meetingWasGood) throws Exception {
        Meeting meeting = meetingDao.getById(meetingId);
        if(meeting == null){
            throw new Exception("No meeting found!");
        }
        meeting.rate(meetingWasGood);
        if(meeting.gotAllResponses()){
            MailHelper.sendResultsMail(mailSender, meeting);
        }

    }


}
