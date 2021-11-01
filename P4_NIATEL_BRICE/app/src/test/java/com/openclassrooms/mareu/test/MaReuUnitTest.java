package com.openclassrooms.mareu.test;

import com.openclassrooms.Model.Meeting;
import com.openclassrooms.Service.MeetingApiService;
import com.openclassrooms.di.DI;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


public class MaReuUnitTest {
    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        //Create meetings
        Meeting meetingToAdd1 = service.createNewMeeting("Meeting room 03", "10:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd2 = service.createNewMeeting("Meeting room 02", "14:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd3 = service.createNewMeeting("Meeting room 08", "08:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");

        service.addMeeting(meetingToAdd1);
        service.addMeeting(meetingToAdd2);
        service.addMeeting(meetingToAdd3);

        //Get list
        List<Meeting> meetings = service.getListMeetings();

        //Test list
        assertThat(meetings.get(0).getLocation(), is("Meeting room 03"));
        assertThat(meetings.get(0).getHour(), is("10:30"));
        assertThat(meetings.get(0).getSubject(), is("Project 6"));
        assertThat(meetings.get(0).getListPeople(), is("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"));

        assertThat(meetings.get(1).getLocation(), is("Meeting room 02"));
        assertThat(meetings.get(1).getHour(), is("14:30"));
        assertThat(meetings.get(1).getSubject(), is("Project 6"));
        assertThat(meetings.get(1).getListPeople(), is("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"));

        assertThat(meetings.get(2).getLocation(), is("Meeting room 08"));
        assertThat(meetings.get(2).getHour(), is("08:30"));
        assertThat(meetings.get(2).getSubject(), is("Project 6"));
        assertThat(meetings.get(2).getListPeople(), is("azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com"));
    }

    @Test
    public void createMeetingWithSuccess() {
        //Create meeting
        Meeting meetingToAdd = service.createNewMeeting("Meeting room 02", "14:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        service.addMeeting(meetingToAdd);
        //Test
        assertThat(service.getListMeetings(), contains(meetingToAdd));

    }

    @Test
    public void sortMeetingByHourWithSuccess() {
        //Create meetings
        Meeting meetingToAdd2 = service.createNewMeeting("Meeting room 02", "14:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd1 = service.createNewMeeting("Meeting room 02", "10:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd3 = service.createNewMeeting("Meeting room 02", "08:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");

        service.addMeeting(meetingToAdd1);
        service.addMeeting(meetingToAdd2);
        service.addMeeting(meetingToAdd3);

        //Sort meetings by hour
        service.sortMeetingByHour();

        //Get list sorted
        List<Meeting> listSorted = service.getListMeetings();

        //Test
        assertThat(listSorted.get(0).getHour(), is("08:30"));
        assertThat(listSorted.get(1).getHour(), is("10:30"));
        assertThat(listSorted.get(2).getHour(), is("14:30"));
    }

    @Test
    public void sortMeetingByLocationWithSuccess() {
        //Create meeting
        Meeting meetingToAdd1 = service.createNewMeeting("Meeting room 21", "10:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd2 = service.createNewMeeting("Meeting room 05", "14:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        Meeting meetingToAdd3 = service.createNewMeeting("Meeting room 65", "08:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");

        service.addMeeting(meetingToAdd1);
        service.addMeeting(meetingToAdd2);
        service.addMeeting(meetingToAdd3);

        //Sort meetings by location
        service.sortMeetingByLocation();

        //Get list sorted
        List<Meeting> listSorted = service.getListMeetings();

        //Test
        assertThat(listSorted.get(0).getLocation(), is("Meeting room 05"));
        assertThat(listSorted.get(1).getLocation(), is("Meeting room 21"));
        assertThat(listSorted.get(2).getLocation(), is("Meeting room 65"));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        //Create meeting
        Meeting meetingToAdd1 = service.createNewMeeting("Meeting room 21", "10:30", "Project 6", "azerty1@gmail.com; azerty2@gmail.com; azerty3@gmail.com");
        service.addMeeting(meetingToAdd1);

        //Delete the meeting created
        Meeting meetingToDelete = service.getListMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertThat(service.getListMeetings(), not(contains(meetingToDelete)));
    }
}