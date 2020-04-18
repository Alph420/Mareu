package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import static android.graphics.Color.rgb;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class DummyMeetingGenerator {

    private static int actualColor;

    public static int getActualColor() {
        return actualColor;
    }

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(generateColor(), "Salle A", generateStartMeeting(), generateEndMeeting(), "Sujet 1", User.listParticipants),
            new Meeting(generateColor(), "Salle B", generateStartMeeting(), generateEndMeeting(), "Sujet 2", User.listParticipants),
            new Meeting(generateColor(), "Salle C", generateStartMeeting(), generateEndMeeting(), "Sujet 3", User.listParticipants),
            new Meeting(generateColor(), "Salle D", generateStartMeeting(), generateEndMeeting(), "Sujet 4", User.listParticipants)
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static int generateColor() {
        actualColor = rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        return actualColor;
    }

    private static Date generateStartMeeting() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        Date date = cal.getTime();
        return date;
    }

    private static Date generateEndMeeting() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        Date date = cal.getTime();
        return date;
    }
}