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
    public boolean checkingMetting(Meeting meeting) {

        List<Meeting> meetingAsTheSameDay = new ArrayList<>();
        for (Meeting mMeetingAsTheSameDay : meetings) {
            if (meeting.getDateStart().getDay() == mMeetingAsTheSameDay.getDateStart().getDay())
                meetingAsTheSameDay.add(mMeetingAsTheSameDay);
        }
        if (meetingAsTheSameDay.size() == 0) return true;

        List<Meeting> meetingAsTheSameRoom = new ArrayList<>();
        for (Meeting mMeetingAsTheSameRoom : meetingAsTheSameDay) {
            if (meeting.getRoom().equals(mMeetingAsTheSameRoom.getRoom())) meetingAsTheSameRoom.add(mMeetingAsTheSameRoom);
        }

        for (Meeting mMeeting : meetingAsTheSameRoom) {
            if (meeting.getDateStart().before(mMeeting.getDateStart()) && meeting.getDateEnd().before(mMeeting.getDateStart())) return true;
            if (meeting.getDateStart().after(mMeeting.getDateEnd()) && meeting.getDateEnd().after(mMeeting.getDateEnd())) return true;
        }
        return false;
    }
}
