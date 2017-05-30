package com.example.piotr.rankingszachowy.Fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.piotr.rankingszachowy.DBHelpers.GameDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.TournamentDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.UserDBHelper;
import com.example.piotr.rankingszachowy.R;

import java.util.Random;

/**
 * Created by Piotr on 30.03.2017.
 */

public class RecentGamesFragment extends Fragment {

    UserDBHelper userDBHelper;
    GameDBHelper gameDBHelper;
    TournamentDBHelper tournamentDBHelper;

    private void CreateTables(View view){

        TableLayout tblRecentGames = (TableLayout) view.findViewById(R.id.tblRecentGames);
        TableLayout tblRecentTournaments = (TableLayout) view.findViewById(R.id.tblRecentTournaments);

        //Setting up Recent Games Rows
        TableRow tbrHeader = new TableRow(getActivity());

        TextView tvPlayers = new TextView(getActivity());
        tvPlayers.setText(view.getResources().getString(R.string.playersHeader_fragment));
        tvPlayers.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tvPlayers);

        TextView tvTournament = new TextView(getActivity());
        tvTournament.setText(view.getResources().getString(R.string.tournamentHeader_fragment));
        tvTournament.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tvTournament);

        TextView tvDate = new TextView(getActivity());
        tvDate.setText(view.getResources().getString(R.string.dateHeader_fragment));
        tvDate.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tvDate);

        tblRecentGames.addView(tbrHeader);
        tblRecentGames.setStretchAllColumns(true);

        //Setting up Recent Tournaments Rows
        TableRow tbrHeader2 = new TableRow(getActivity());

        TextView tvTournament2 = new TextView(getActivity());
        tvTournament2.setText(view.getResources().getString(R.string.tournamentHeader_fragment));
        tvTournament2.setBackgroundColor(Color.LTGRAY);
        tbrHeader2.addView(tvTournament2);

        TextView tvLastGame = new TextView(getActivity());
        tvLastGame.setText(view.getResources().getString(R.string.lastGameHeader_fragment));
        tvLastGame.setBackgroundColor(Color.LTGRAY);
        tbrHeader2.addView(tvLastGame);

        tblRecentTournaments.addView(tbrHeader2);
        tblRecentTournaments.setStretchAllColumns(true);

        AddContent(tblRecentGames, tblRecentTournaments);
    }

    //Used for filling tables with crap
    private void AddContent(TableLayout table, TableLayout table2){

        TableRow row;
        TextView tv;
        Random rng = new Random();
        Cursor resU = userDBHelper.getAllData();
        Cursor resT = tournamentDBHelper.getAllData();
        Cursor resG = gameDBHelper.getAllData();

        while(resU.moveToNext()) {
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setText(resU.getString(1));
            row.addView(tv);
            tv = new TextView(getActivity());
            if(resT.moveToNext()) {
                tv.setText(resT.getString(1));
            }else
                tv.setText(" ");
            row.addView(tv);
            tv = new TextView(getActivity());
            tv.setText(resU.getString(3));
            row.addView(tv);

            table.addView(row);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recentgames, container, false);

        userDBHelper = new UserDBHelper(getActivity());
        tournamentDBHelper = new TournamentDBHelper(getActivity());
        gameDBHelper = new GameDBHelper(getActivity());
        CreateTables(view);




        return view;
    }


}
