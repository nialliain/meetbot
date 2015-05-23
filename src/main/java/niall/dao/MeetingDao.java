package niall.dao;

import niall.domain.Meeting;
import org.joda.time.LocalDateTime;

import java.util.Set;
import java.util.HashSet;

public class MeetingDao {

    private Set<Meeting> meetings;

    public MeetingDao(){
        meetings = new HashSet<Meeting>();
    }

    public synchronized void add(Meeting meeting){
        meeting.setId(meetings.size()+1);
        meetings.add(meeting);
    }

    public Set<Meeting> getAll(){
        return meetings;
    }

    public Meeting getById(long id){
        for(Meeting meeting : meetings){
            if(meeting.getId() == id){
                return meeting;
            }
        }
        return null;
    }

    public Set<Meeting> getPendingEnd(){
        Set<Meeting> returnSet = new HashSet<Meeting>();
        for(Meeting meeting : meetings){
            if(!meeting.isEnded() && LocalDateTime.now().compareTo(meeting.getEnd()) >= 0){
                returnSet.add(meeting);
            }
        }
        return returnSet;
    }

}
