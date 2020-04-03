package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.ParticipantsGenerator;

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

    private static int hoursMeeting = 14;

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(generateColor(), hoursMeeting, "Salle test 1", "Sujet", ParticipantsGenerator.adressGenerator()),
            new Meeting(generateColor(), hoursMeeting, "Salle test 2", "Sujet", ParticipantsGenerator.adressGenerator()),
            new Meeting(generateColor(), hoursMeeting, "Salle test 3", "Sujet", ParticipantsGenerator.adressGenerator()),
            new Meeting(generateColor(), hoursMeeting, "Salle test 4", "Sujet", ParticipantsGenerator.adressGenerator())
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static int generateColor() {
        actualColor = rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        return actualColor;
    }
}