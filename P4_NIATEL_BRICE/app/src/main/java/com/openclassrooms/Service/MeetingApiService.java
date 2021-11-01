package com.openclassrooms.Service;

import com.openclassrooms.Model.Meeting;

import java.util.List;


/**
 * Neighbour API client
 */
public interface MeetingApiService {

    List<Meeting> getListMeetings();

    void deleteMeeting(Meeting neighbour);

    Meeting createNewMeeting(String location, String hour, String subject, String listPeople);

    boolean sortMeetingByHour();

    boolean sortMeetingByLocation();

    void addMeeting(Meeting meeting);
}
