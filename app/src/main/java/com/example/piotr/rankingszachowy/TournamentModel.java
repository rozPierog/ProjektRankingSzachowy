package com.example.piotr.rankingszachowy;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by Piotr on 02.04.2017.
 */

public class TournamentModel {

    public enum TournamentState{
        PreTournament,
        OnGoing,
        Finished
    }

    private String title;
    private String image;
    private String description;
    private int playersCap;
    private Boolean isOpenTournament;
    private Date StartDate;
    private Date EndDate;
    private TournamentState currentState;

    //<Placement, playerIndex>
    private ArrayMap<Integer, Integer> Placements;
    private List<PlayerModel> PlayersInvited;
    private List<Boolean> PlayerConfirms;

    public TournamentModel(){
        title = "Placeholder title";
        description = "Placeholder description";
        playersCap = 10;
        isOpenTournament = true;
        currentState = TournamentState.PreTournament;

        Placements = new ArrayMap<>();
        PlayersInvited = new ArrayList<>();
        PlayerConfirms = new ArrayList<>();
    }

    public void InvitePlayer(PlayerModel player){

        if(playersCap > PlayersInvited.size() && !PlayersInvited.contains(player) ){
            PlayersInvited.add(player);
            PlayerConfirms.add(false);
        }
    }

    public void JoinPlayer(PlayerModel player){

        if(playersCap > PlayersInvited.size() && !PlayersInvited.contains(player) && isOpenTournament){
            PlayersInvited.add(player);
            PlayerConfirms.add(true);
        }
    }

    public void ConfirmPlayer(PlayerModel player){

        if(PlayersInvited.contains(player)){
            PlayerConfirms.set(PlayersInvited.indexOf(player), true);
        }
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public List<PlayerModel> getPlayersInvited() {
        return PlayersInvited;
    }

    public void setPlayersInvited(List<PlayerModel> playersInvited) {
        PlayersInvited = playersInvited;
    }

    public List<Boolean> getPlayerConfirms() {
        return PlayerConfirms;
    }

    public void setPlayerConfirms(List<Boolean> playerConfirms) {
        PlayerConfirms = playerConfirms;
    }

    public Boolean getOpenTournament() {
        return isOpenTournament;
    }

    public void setOpenTournament(Boolean openTournament) {
        isOpenTournament = openTournament;
    }

    public int getPlayersCap() {
        return playersCap;
    }

    public void setPlayersCap(int playersCap) {
        this.playersCap = playersCap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTournamentName() {
        return title;
    }

    public void setTournamentName(String tournamentName) {
        this.title = tournamentName;
    }


    public ArrayMap<Integer, Integer> getPlacements() {
        return Placements;
    }

    public TournamentState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(TournamentState currentState) {
        this.currentState = currentState;
    }
}
