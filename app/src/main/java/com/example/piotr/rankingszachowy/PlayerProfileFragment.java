package com.example.piotr.rankingszachowy;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Piotr on 02.04.2017.
 */

public class PlayerProfileFragment extends Fragment {


    private Boolean isEditMode = false;
    private EditText etUsername;
    private EditText etAge;
    private EditText etRank;
    //private LinearLayout linEditImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playerprofile, container, false);

        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etAge = (EditText) view.findViewById(R.id.etAge);
        etRank = (EditText) view.findViewById(R.id.etRank);
        //linEditImage = (LinearLayout) view.findViewById(R.id.linEditImage);
        setEditMode(false);

        ImageButton btnEdit = (ImageButton) view.findViewById(R.id.btnEditProfile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setEditMode(!isEditMode);

            }
        });


        CreateTables(view);


        return view;
    }

    private void CreateTables(View view){

        TableLayout tblAchievements = (TableLayout) view.findViewById(R.id.tblAchivementsPlayerProfile);
        TableLayout tblHistory = (TableLayout) view.findViewById(R.id.tblGamesHistoryPlayerProfile);

        TableRow tbrHeader = new TableRow(getActivity());

        TextView tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.tournamentHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.placementHeader_playerProfile_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tblAchievements.addView(tbrHeader);
        tblAchievements.setStretchAllColumns(true);

        tbrHeader = new TableRow(getActivity());

        tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.playersHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.tournamentHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);


        tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.dateHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tblAchievements.addView(tbrHeader);
        tblAchievements.setStretchAllColumns(true);


        AddContent(tblAchievements, tblHistory);
    }

    //Used for filling tables with crap
    private void AddContent(TableLayout table, TableLayout table2){

        TableRow row;
        TextView tv;
        Random rng = new Random();

        for (int i = 0; i < 15; i++) {
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Row " + i + ": " + rng.nextInt(300));
            row.addView(tv);

            table.addView(row);

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

            table2.addView(row);
        }
    }

    public void setEditMode(Boolean editMode) {

        isEditMode = editMode;

        etUsername.setFocusable(isEditMode);
        etUsername.setClickable(isEditMode);

        etRank.setFocusable(isEditMode);
        etRank.setClickable(isEditMode);

        etAge.setFocusable(isEditMode);
        etAge.setClickable(isEditMode);
//        if(isEditMode){
//            linEditImage.setVisibility(View.VISIBLE);
//        } else {
//            linEditImage.setVisibility(View.GONE);
//        }

        etUsername.setClickable(isEditMode);
    }
}
