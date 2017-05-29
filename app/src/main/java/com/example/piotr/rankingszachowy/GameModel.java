package com.example.piotr.rankingszachowy;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Piotr on 28.05.2017.
 */

public class GameModel {



    public PlayerModel WhitePlayer;
    public PlayerModel BlackPlayer;
    public int WhitePoints;
    public int BlackPoints;
    public Date MatchDate;
    public ArrayList<String> MovesList;
    public TournamentModel Tournament;

    public GameModel(PlayerModel whitePlayer, PlayerModel blackPlayer, int whitePoints, int blackPoints, Date matchDate, TournamentModel tournament) {

        WhitePlayer = whitePlayer;
        BlackPlayer = blackPlayer;
        WhitePoints = whitePoints;
        BlackPoints = blackPoints;
        MatchDate = matchDate;
        MovesList = new ArrayList<>();
        Tournament = tournament;
    }
}
