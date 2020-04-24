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

    private Room(String roomName) {
        this.name = roomName;
    }

    private String getRoomName() {
        return name;
    }

    private static List<Room> ListRooms = Arrays.asList(
            new Room("Salle A"),
            new Room("Salle B"),
            new Room("Salle C"),
            new Room("Salle D")
    );

    static public List<String> getSalle() {
        List<String> roomList = new ArrayList<>();
        for (Room room : ListRooms) {
            roomList.add(room.getRoomName());
        }
        return roomList;
    }
}