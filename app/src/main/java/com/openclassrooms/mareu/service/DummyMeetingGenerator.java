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
public abstract class DummyMeetingGenerator{

   private static int hoursMeeting = 14;

   private static List<String> mStringList = ParticipantsGenerator.adressGenerator();
   public static final int  color = rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));



    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(color, hoursMeeting,"Salle test 1","Sujet",mStringList),
            new Meeting(color, hoursMeeting,"Salle test 2","Sujet",mStringList),
            new Meeting(color, hoursMeeting,"Salle test 3","Sujet",mStringList),
            new Meeting(color, hoursMeeting,"Salle test 4","Sujet",mStringList)


    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}
