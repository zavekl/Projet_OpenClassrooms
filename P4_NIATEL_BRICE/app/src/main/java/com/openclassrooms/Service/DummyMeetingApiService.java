package com.openclassrooms.Service;

import com.openclassrooms.Model.Meeting;

import java.util.Collections;
import java.util.List;

import static com.openclassrooms.Service.DummyMeetingGenerator.generateMeeting;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> listNeighbours = generateMeeting();
    private int ID = 0;

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Meeting> getListMeetings() {
        return listNeighbours;
    }

    @Override
    public void deleteMeeting(Meeting neighbour) {
        listNeighbours.remove(neighbour);
    }

    @Override
    public Meeting createNewMeeting(String location, String hour, String subject, String listPeople) {
        ID = ID + 1;

        return new Meeting(ID, location, hour, subject, listPeople);
    }


    @Override
    public boolean sortMeetingByHour() {
        if (listNeighbours.size() != 0) {
            Collections.sort(listNeighbours, (meeting1, meeting2) -> meeting1.getHour().compareTo(meeting2.getHour()));
            return true;
        } else {
            return false;

        }
    }

    @Override
    public boolean sortMeetingByLocation() {
        if (listNeighbours.size() != 0) {
            Collections.sort(listNeighbours, (meeting1, meeting2) -> meeting1.getLocation().compareTo(meeting2.getLocation()));
            return true;
        } else {
            return false;

        }
    }

    @Override
    public void addMeeting(Meeting meeting) {
        listNeighbours.add(meeting);
    }


}
