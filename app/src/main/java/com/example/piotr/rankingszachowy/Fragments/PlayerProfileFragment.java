package com.example.piotr.rankingszachowy.Fragments;

import android.app.Fragment;
import android.database.Cursor;
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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotr.rankingszachowy.DBHelpers.UserDBHelper;
import com.example.piotr.rankingszachowy.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Piotr on 02.04.2017.
 */

public class PlayerProfileFragment extends Fragment {


    private Boolean isEditMode = false;
    private EditText etUsername;
    private EditText etAge;
    private EditText etRank;
    private EditText etLastPlayed;
    private EditText etPlayingSince;
    private Cursor result;
    private Spinner spinnerPlayer;
    UserDBHelper userDB;
    ImageButton btnEdit;

    ArrayList<String> nicks;

    //private LinearLayout linEditImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playerprofile, container, false);

        userDB = new UserDBHelper(getActivity());
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etAge = (EditText) view.findViewById(R.id.etAge);
        etRank = (EditText) view.findViewById(R.id.etRank);
        etLastPlayed = (EditText) view.findViewById(R.id.etLastPlayed);
        etPlayingSince = (EditText) view.findViewById(R.id.etPlayingSince);

        spinnerPlayer = (Spinner) view.findViewById(R.id.spinnerPlayer);

        nicks = new ArrayList<>();
        getNicks();

        final ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, nicks);
        spinnerPlayer.setAdapter(adapter2);

        spinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    clearEditText();
                }
                setUser((String) spinnerPlayer.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getAll();

        btnEdit = (ImageButton) view.findViewById(R.id.btnEditProfile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditMode = !isEditMode;
                setEditMode(isEditMode);
                if (!isEditMode) {
                    boolean isInserted = userDB.insertData(etUsername.getText().toString(),
                            etRank.getText().toString(),
                            etLastPlayed.getText().toString(),
                            etPlayingSince.getText().toString(),
                            etAge.getText().toString());
                    if (isInserted) {
                        Toast.makeText(getActivity(), "Added to database", Toast.LENGTH_SHORT).show();
                        getNicks();
                        adapter2.notifyDataSetChanged();
                        spinnerPlayer.setSelection(nicks.size());
                    }
                    else
                        Toast.makeText(getActivity(), "Derp to database", Toast.LENGTH_SHORT).show();
                }

            }
        });


        CreateTables(view);


        return view;
    }

    private void clearEditText() {
        etUsername.setText("");
        etAge.setText("");
        etRank.setText("");
        etLastPlayed.setText("");
        etPlayingSince.setText("");
    }

    private void getNicks() {
        result = userDB.getAllData();
        nicks.clear();
        nicks.add("+ Add Player +");
        if (result.getCount() == 0) {
            Toast.makeText(getActivity(), "No records of users in database", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {
                nicks.add(result.getString(1));
            }
        }
    }

    private void setUser(String username) {
        result = userDB.getSpecificData(username);
        if (result.getCount() == 0) {
            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
        } else {
            result.moveToNext();
            etUsername.setText(result.getString(1));
            etRank.setText(result.getString(2));
            etLastPlayed.setText(result.getString(3));
            etPlayingSince.setText(result.getString(4));
            etAge.setText(result.getString(5));
        }
    }

    private void getAll() {
        Cursor res = userDB.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                etUsername.setText(res.getString(1));
                etAge.setText(res.getString(5));
                etRank.setText(res.getString(2));
                etLastPlayed.setText(res.getString(3));
                etPlayingSince.setText(res.getString(4));

            }
        }
    }

    private void CreateTables(View view) {

//        TableLayout tblAchievements = (TableLayout) view.findViewById(R.id.tblAchivementsPlayerProfile);
//        TableLayout tblHistory = (TableLayout) view.findViewById(R.id.tblGamesHistoryPlayerProfile);

        TableRow tbrHeader = new TableRow(getActivity());

        TextView tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.tournamentHeader_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

        tv = new TextView(getActivity());
        tv.setText(view.getResources().getString(R.string.placementHeader_playerProfile_fragment));
        tv.setBackgroundColor(Color.LTGRAY);
        tbrHeader.addView(tv);

//        tblAchievements.addView(tbrHeader);
//        tblAchievements.setStretchAllColumns(true);

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

//        tblAchievements.addView(tbrHeader);
//        tblAchievements.setStretchAllColumns(true);


//        AddContent(tblAchievements, tblHistory);
    }

    public void setEditMode(Boolean editMode) {

        isEditMode = editMode;

        etUsername.setFocusable(isEditMode);
        etUsername.setClickable(isEditMode);

        etRank.setFocusable(isEditMode);
        etRank.setClickable(isEditMode);

        etPlayingSince.setFocusable(isEditMode);
        etPlayingSince.setClickable(isEditMode);

        etLastPlayed.setFocusable(isEditMode);
        etLastPlayed.setClickable(isEditMode);

        etAge.setFocusable(isEditMode);
        etAge.setClickable(isEditMode);
        if(isEditMode)
            btnEdit.setImageResource(R.drawable.ic_save);
        else
            btnEdit.setImageResource(R.drawable.ic_create);

    }
}
