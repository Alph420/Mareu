package com.openclassrooms.mareu.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class Meeting {

    private int color;

    private String hoursMeetingStart;

    private String hoursMeetingEnd;

    private String room;

    private Date date;

    private String sujet;

    private List<String> participantsList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public String getHoursMeetingStart() {
        return hoursMeetingStart;
    }

    public void setHoursMeetingStart(String hoursMeetingStart) {
        this.hoursMeetingStart = hoursMeetingStart;
    }

    public String getHoursMeetingEnd() {
        return hoursMeetingEnd;
    }

    public void setHoursMeetingEnd(String hoursMeetingEnd) {
        this.hoursMeetingEnd = hoursMeetingEnd;
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

    public String getParticipantsList() {
        String participants = "";
        for (String participant : participantsList) {
            participants += participant + ", ";
        }
        return participants;
    }

    public void setParticipantsList(List<String> participantsList) {
        this.participantsList = participantsList;
    }


    public String getInfo() {
        return this.getRoom() + " - " + this.getHoursMeetingStart() + " - " + this.getSujet();
    }

    public Meeting(int color, String hoursMeetingStart, String hoursMeetingEnd, String room, Date date, String sujet, List<String> participantsList) {
        this.color = color;
        this.hoursMeetingStart = hoursMeetingStart;
        this.hoursMeetingEnd = hoursMeetingEnd;
        this.room = room;
        this.date = date;
        this.sujet = sujet;
        this.participantsList = participantsList;
    }
}
