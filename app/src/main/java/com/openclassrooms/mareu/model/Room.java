package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alph4 le 02/04/2020.
 * Projet: Mareu
 **/
public class Room {

    private String name;

    public Room(String roomName) {
        this.name = roomName;
    }

    public String getRoomName() {
        return name;
    }

    static List<Room> sListRooms = Arrays.asList(
            new Room("Salle A"),
            new Room("Salle B"),
            new Room("Salle C"),
            new Room("Salle D")
    );

    static public List<String> getSalle() {
        List<String> listSalle = new ArrayList<>();
        for (Room room : sListRooms) {
            listSalle.add(room.getRoomName());
        }
        return listSalle;
    }
}