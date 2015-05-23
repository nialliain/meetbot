package niall.domain;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class Meeting {

    private long id;
    private String subject, organiser;
    private List<String> participants;
    private List<Boolean> ratings;
    private LocalDateTime start, end;
    boolean ended, timedOut;

    public Meeting(String subject, String organiser, List<String> participants, LocalDateTime start, LocalDateTime end){
        this.setSubject(subject);
        this.setOrganiser(organiser);
        this.setParticipants(participants);
        this.setStart(start);
        this.setEnd(end);
        ended = false;
        timedOut = false;
        ratings = new ArrayList<Boolean>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString(){
        return new StringBuilder("Meeting: [").append(subject).append(" called by ").append(organiser).append(", ").append(start).append(" to ").append(end).append(". Participants: ").append(participants).toString();
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Meeting) || o == null){
            return  false;
        }
        Meeting otherMeeting = (Meeting)o;
        return this.toString().equals(otherMeeting.toString());
    }

    public boolean isTimedOut() {
        return timedOut;
    }

    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void rate(boolean meetingWasGood) {
        ratings.add(meetingWasGood);
    }

    public boolean gotAllResponses() {
        return ratings.size()==participants.size();
    }

    public String getSuccess() {
        int positiveRatings = 0;
        for(boolean b:ratings){
            if(b){
                positiveRatings++;
            }
        }
        return Double.toString(positiveRatings*100.0/ratings.size())+"%";
    }
}
