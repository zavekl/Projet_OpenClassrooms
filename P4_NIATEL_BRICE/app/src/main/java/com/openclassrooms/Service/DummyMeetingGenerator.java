package com.openclassrooms.Service;

import com.openclassrooms.Model.Meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DummyMeetingGenerator {

    private static List<Meeting> ListMeetings = Collections.emptyList();

    static ArrayList<Meeting> generateMeeting() {
        return new ArrayList<>(ListMeetings);
    }

}