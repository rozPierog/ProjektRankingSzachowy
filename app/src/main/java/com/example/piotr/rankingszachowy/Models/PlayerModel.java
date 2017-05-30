package com.example.piotr.rankingszachowy.Models;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Piotr on 02.04.2017.
 */

public class PlayerModel {

    public String Username;
    public int Rank;
    public Date LastPlayed;
    public Date PlayingSince;
    public int Age;

    public PlayerModel(String username, int rank, Date last, Date since, int age){

        this.Username = username;
        this.Rank = rank;
        this.LastPlayed = last;
        this.PlayingSince = since;
        this.Age = age;

    }


}
