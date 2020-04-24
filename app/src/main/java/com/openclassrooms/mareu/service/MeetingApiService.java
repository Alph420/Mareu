package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public interface MeetingApiService {

    List<Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    boolean checkingMeeting(Meeting meeting);

    List<Meeting> getMeetingDateFilter(Date date);

    List<Meeting> getMeetingRoomFilter(String room);

}
