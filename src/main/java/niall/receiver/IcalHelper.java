package niall.receiver;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import niall.domain.Meeting;
import org.joda.time.LocalDateTime;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class IcalHelper {

    Logger LOG = Logger.getLogger(IcalHelper.class);

    public static List<Meeting> decodeIcal(String icalString){
        List<Meeting> decodedMeetings = new LinkedList<Meeting>();
        ICalendar ical = Biweekly.parse(icalString).first();
        for(VEvent event : ical.getEvents()) {
            String organiser = event.getOrganizer().getCommonName();
            List<String> participants = new LinkedList<String>();
            for (Attendee attendee : event.getAttendees()) {
                participants.add(attendee.getCommonName());
            }
            String subject = event.getSummary().getValue();
            LocalDateTime start = LocalDateTime.fromDateFields(event.getDateStart().getValue().getRawComponents().toDate());
            LocalDateTime end = LocalDateTime.fromDateFields(event.getDateEnd().getValue().getRawComponents().toDate());
            decodedMeetings.add(new Meeting(subject, organiser, participants, start, end));
        }
        return decodedMeetings;
    }

}
