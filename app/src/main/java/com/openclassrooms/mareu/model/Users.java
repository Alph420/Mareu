package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Alph4 le 31/03/2020.
 * Projet: Mareu
 **/
public class Users {

    private String prenom;
    private String nom;
    private static String adresseEmail = "@service.com";

    public Users(String prenom, String nom,String adresseEmail) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresseEmail = adresseEmail;
    }

    public static List<Users> DUMMY_USERS = Arrays.asList(
            new Users("Philippe","Ginenet",adresseEmail),
            new Users("Raphaël","Cardaimont",adresseEmail),
            new Users("Gabriel","Alirral",adresseEmail),
            new Users("Arthur","Creleilles",adresseEmail)

    );


    public static List<String> listParticipants = adressGenerator();
    public static List<String> listPrenom = prenomGenerator();
    public static List<String> listNom = nomGenerator();



    public static List adressGenerator() {
        List<String> tab = new ArrayList<>();
        for (Users utilisateur : DUMMY_USERS) {
            tab.add(utilisateur.prenom + utilisateur.nom + utilisateur.adresseEmail);
        }
        return tab;
    }

    public static List prenomGenerator() {
        List<String> tab = new ArrayList<>();
        for (Users utilisateur : DUMMY_USERS) {
            tab.add(utilisateur.prenom );
        }
        return tab;
    }
    public static List nomGenerator() {
        List<String> tab = new ArrayList<>();
        for (Users utilisateur : DUMMY_USERS) {
            tab.add(utilisateur.nom);
        }
        return tab;
    }


}
