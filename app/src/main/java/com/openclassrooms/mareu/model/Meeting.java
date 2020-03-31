package com.openclassrooms.mareu.model;

import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class Meeting {


    private int color;

    private int hoursMeeting;

    private String room;

    private String sujet;

    private List<String> participantsList;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public int getHoursMeeting() {
        return hoursMeeting;
    }

    public void setHoursMeeting(int hoursMeeting) {
        this.hoursMeeting = hoursMeeting;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public List<String> getParticipantsList() {
        return participantsList;
    }

    public void setParticipantsList(List<String> participantsList) {
        this.participantsList = participantsList;
    }


    public String getInfo(){
        return this.getRoom() + this.getHoursMeeting() + this.getSujet();
    }

    public Meeting(int color, int hourMeeting, String room, String sujet, List<String> participantsList) {
        this.color = color;
        this.hoursMeeting = hourMeeting;
        this.room = room;
        this.sujet = sujet;
        this.participantsList = participantsList;
    }
}
