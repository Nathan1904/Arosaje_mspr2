package com.example.arosaje.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance = new AppData();
    
    public static AppData getInstance(){return instance;}
    
    public static void setInstance(AppData instance) {AppData.instance = instance;}
    
    public Integer courantUserId;
    public List<User> listUsers;
    public Integer nbUsers;
    public List<Plante> listPlantes;
    public Integer nbPlantes;
    public String imageTemp = "";
    public Integer nbGardiennages;
    public List<Gardiennage> listGardiennage;


    private AppData() {
        //création d'utilisateurs test
        listUsers = new ArrayList<>();
        courantUserId = 0;
        nbUsers = 5;
        for (int i = 0; i < nbUsers; i++){
            User user = new User ("USER "+String.valueOf(i), i, GardiennageEtat.AUCUN);
            Log.i("DEBUG", user.getNomUtilisateur());
            listUsers.add(user);
        }
        //création de liste de plante
        listPlantes = new ArrayList<>();
        nbPlantes = 10;
        for (Integer i = 0; i < nbPlantes; i++){
            Plante plante = new Plante ("Plante "+String.valueOf(i), i, i, "","2023-02-03T"+String.valueOf(i)+"15:30", "Paris", "aucun");
            listPlantes.add(plante);
        }

        //création liste gardiennage
        listGardiennage = new ArrayList<>();
        nbGardiennages = 0;
    }
}