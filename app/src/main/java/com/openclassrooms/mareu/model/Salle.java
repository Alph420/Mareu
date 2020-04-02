package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alph4 le 02/04/2020.
 * Projet: Mareu
 **/
public class Salle {
    private String salleName;


    public Salle(String salleName) {
        this.salleName = salleName;
    }

    public String getSalleName() {
        return salleName;
    }

    static List<Salle> listSalles = Arrays.asList(
            new Salle("Salle A"),
            new Salle("Salle B"),
            new Salle("Salle C"),
            new Salle("Salle D")
    );


    static public List<String> getSalle() {
        List<String> listSalle = new ArrayList<>();
        for (Salle salle : listSalles) {
            listSalle.add(salle.getSalleName());
        }
        return listSalle;
    }

}
