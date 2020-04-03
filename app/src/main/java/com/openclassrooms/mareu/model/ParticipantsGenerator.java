package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alph4 le 31/03/2020.
 * Projet: Mareu
 **/
public class ParticipantsGenerator {

    private static Random rnd = new Random();
    private static String prenomTab[] = {"Gabriel" ,"Raphaël" ,"Léo" ,"Louis", "Lucas" ,"Adam" ,"Arthur" ,"Hugo"};
    private static String nomTab[] = {"Creleilles","Chagnac", "Gaissier", "Chaubannes", "Ginenet", "Clarithier", "Cardaimont", "Alirral"};
    public static List<String> listParticipants = adressGenerator();


    //Generateur d'adresse email
    public static List adressGenerator() {
       List<String> tab = new ArrayList<>();
        String adress;

        for (int i=0;i<4;i++) {
            String prenom = prenomTab[rnd.nextInt(prenomTab.length)];
            String nom = nomTab[rnd.nextInt(nomTab.length)];
            adress = prenom + nom + "@service.com";
            tab.add(adress);
        }
            return tab;
    }
}
