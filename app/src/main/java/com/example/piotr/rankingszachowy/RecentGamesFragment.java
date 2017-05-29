package com.example.piotr.rankingszachowy;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by Piotr on 30.03.2017.
 */

public class RecentGamesFragment extends Fragment {


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

        for (int i = 0; i < 5; i++) {
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);

            table.addView(row);

            tv = new TextView(getActivity());
            row = new TableRow(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);

            table2.addView(row);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recentgames, container, false);

        CreateTables(view);


        return view;
    }


}
