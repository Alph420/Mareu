package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.rgb;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public abstract class DummyMeetingGenerator {

    private static int actualColor;

    public static int getActualColor() {
        return actualColor;
    }

    private static String hoursMeeting = "08";
    private static Date date = new Date();


    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(generateColor(), hoursMeeting+'h', "09h", "Salle A",date, "Sujet 1", Users.listParticipants),
            new Meeting(generateColor(),hoursMeeting+'h', "09h", "Salle B",date, "Sujet 2", Users.listParticipants),
            new Meeting(generateColor(),hoursMeeting+'h', "09h", "Salle C",date, "Sujet 3", Users.listParticipants),
            new Meeting(generateColor(), hoursMeeting+'h', "09h", "Salle E",date, "Sujet 4", Users.listParticipants)
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static int generateColor() {
        actualColor = rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        return actualColor;
    }
}