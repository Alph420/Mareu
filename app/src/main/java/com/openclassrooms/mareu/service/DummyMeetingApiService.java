package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public boolean chekingMetting(Meeting meeting) {
        List<Meeting> meetingAsTheSameDay = new ArrayList<>();
        List<Meeting> meetingAsTheSameRoom = new ArrayList<>();
        List<Meeting> meetingAsTheSameHoursStarting= new ArrayList<>();
        List<Meeting> meetingAsTheSameHoursEnding= new ArrayList<>();

        Meeting meetingAsVerify = meeting;

        //verifier si c la meme jour
        //verifier la salle
        //verifier la via une timeline
        // trois condition

        return true;
    }
}
