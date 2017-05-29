package com.example.piotr.rankingszachowy.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.piotr.rankingszachowy.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Piotr on 02.04.2017.
 */

public class AddGameFragment extends Fragment {


    private String[] _sides;
    private Spinner _sideSpinnerPlayer;
    private Spinner _sideSpinnerOpponent;

    private String playerSide;
    private String opponentSide;

    public void setPlayerSide(String playerSide) {
        this.playerSide = playerSide;

        if (opponentSide == playerSide){
            if(opponentSide == _sides[0])
                opponentSide = _sides[1];
            else{
                opponentSide = _sides[0];
            }
        }
    }
    public void setOpponentSide(String opponentSide) {
        this.opponentSide = opponentSide;

        if (opponentSide == playerSide){
            if(playerSide == _sides[0])
                playerSide = _sides[1];
            else{
                playerSide = _sides[0];
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addgame, container, false);

        _sideSpinnerPlayer = (Spinner) view.findViewById(R.id.spinnerPlayer);
        _sideSpinnerOpponent = (Spinner) view.findViewById(R.id.spinnerPlayer);
        _sides = new String[] {view.getResources().getString(R.string.sideWhite), view.getResources().getString(R.string.sideBlack)};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, _sides);

        _sideSpinnerOpponent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOpponentSide( (String) _sideSpinnerOpponent.getItemAtPosition(position) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _sideSpinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPlayerSide( (String) _sideSpinnerPlayer.getItemAtPosition(position) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        CreateTables(view);


        return view;
    }

    private void CreateTables(View view){

        TableLayout tblMoves = (TableLayout) view.findViewById(R.id.tblMovesListAddGame);

        //Setting up Open Tournaments Rows
        TableRow tbrHeader = new TableRow(getActivity());

        TextView tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.addMoveHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tblMoves.addView(tbrHeader);
        tblMoves.setStretchAllColumns(true);

        AddContent(tblMoves);
    }

    //Used for filling tables with crap
    private void AddContent(TableLayout table){

        TableRow row;
        TextView tv;
        Random rng = new Random();

        for (int i = 0; i < 15; i++) {
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);

            table.addView(row);
        }
    }
}
