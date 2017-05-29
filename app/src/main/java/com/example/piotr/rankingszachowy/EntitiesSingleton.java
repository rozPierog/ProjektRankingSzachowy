package com.example.piotr.rankingszachowy;

import android.util.ArrayMap;

import com.example.piotr.rankingszachowy.Models.GameModel;
import com.example.piotr.rankingszachowy.Models.PlayerModel;
import com.example.piotr.rankingszachowy.Models.TournamentModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Piotr on 29.05.2017.
 */

 class EntitiesSingleton {

    public ArrayMap<String, PlayerModel> Players;
    public ArrayMap<String, TournamentModel> Tournaments;
    public ArrayMap<String, GameModel> Games;

    private static final EntitiesSingleton ourInstance = new EntitiesSingleton();

    static EntitiesSingleton getInstance() { return ourInstance; }

    private EntitiesSingleton() {

        Players = new ArrayMap<>();
        Tournaments = new ArrayMap<>();
        Games = new ArrayMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy", Locale.GERMAN);


        //String username, int rank, Date last, Date since, int age, Bitmap av
        //PlayerModel = new PlayerModel("Jarek", 3400, sdf.format(new Date()), sdf.parse("28-04-2017"), 23);
    }

}
