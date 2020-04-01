package com.openclassrooms.mareu.events;
import com.openclassrooms.mareu.model.Meeting;


/**
 * Created by Alph4 le 01/04/2020.
 * Projet: Mareu
 **/
public class DeleteMeetingEvent {


    /**
     * Neighbour to delete
     */
    public Meeting meeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting =  meeting;
    }
}
