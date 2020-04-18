package com.openclassrooms.mareu.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alph4 le 26/03/2020.
 * Projet: Mareu
 **/
public class Meeting {

    private int color;


    private String room;

    private Date dateStart;

    private Date dateEnd;


    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    private String subject;

    private List<String> participantsList;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setHours( this.dateStart.getHours());
        date.setMinutes(this.dateStart.getMinutes());

        return this.getRoom() + " - " + dateFormat.format(date).replace(':','h') + " - " + this.getSubject();
    }

    public Meeting(int color, String room, Date dateStart, Date dateEnd, String subject, List<String> participantsList) {
        this.color = color;
        this.room = room;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.subject = subject;
        this.participantsList = participantsList;
    }
}
