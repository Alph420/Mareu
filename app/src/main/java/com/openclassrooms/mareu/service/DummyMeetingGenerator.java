package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Users;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static String hoursMeeting = "14";
    private static String minutesMeeting = "30";

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(generateColor(), hoursMeeting+'h'+ minutesMeeting, "15h30", "Salle test 1", "Sujet", Users.listParticipants),
            new Meeting(generateColor(),hoursMeeting+'h'+ minutesMeeting, "15h30", "Salle test 2", "Sujet", Users.listParticipants),
            new Meeting(generateColor(),hoursMeeting+'h'+ minutesMeeting, "15h30", "Salle test 3", "Sujet", Users.listParticipants),
            new Meeting(generateColor(), hoursMeeting+'h'+ minutesMeeting, "15h30", "Salle test 4", "Sujet", Users.listParticipants)
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static int generateColor() {
        actualColor = rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        return actualColor;
    }
}