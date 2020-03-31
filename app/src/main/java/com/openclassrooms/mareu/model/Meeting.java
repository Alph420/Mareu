package com.openclassrooms.mareu.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class Meeting {


    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    private Date date;

    private String room;

    private String sujet;

    private List<String> participantsList;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return this.getRoom() + this.getDate() + this.getSujet();
    }

    public Meeting(int color, Date date, String room, String sujet, List<String> participantsList) {
        this.color = color;
        this.date = date;
        this.room = room;
        this.sujet = sujet;
        this.participantsList = participantsList;
    }
}
