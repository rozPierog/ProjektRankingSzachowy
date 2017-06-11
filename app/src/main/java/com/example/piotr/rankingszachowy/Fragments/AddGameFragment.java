package com.example.piotr.rankingszachowy.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.piotr.rankingszachowy.DBHelpers.GameDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.TournamentDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.UserDBHelper;
import com.example.piotr.rankingszachowy.Models.PlayerModel;
import com.example.piotr.rankingszachowy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Piotr on 02.04.2017.
 */

public class AddGameFragment extends Fragment {


    GameDBHelper gameDBHelper;
    UserDBHelper userDBHelper;
    TournamentDBHelper tournamentDBHelper;
    Cursor result;

    private String[] _sides;
    private Spinner sideSpinnerPlayer;
    private Spinner sideSpinnerOpponent;

    private String playerSide;
    private String opponentSide;
    private PlayerModel whitePlayer;
    private PlayerModel blackPlayer;
    private int whitePoints = 0;
    private int blackPoints = 0;
    private Date matchDate;
    private ArrayList<String> movesList;
    private String tournamentID;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;


    ArrayList<String> nicks;
    ArrayList<String> tours;

    Spinner spinnerResult;
    Spinner spinnerTour;
    EditText etDate;
    EditText etWhiteMove;
    EditText etBlackMove;
    Button buttonAdd;
    Button buttonConfirm;

    TableLayout table;

//    public void setPlayerSide(String playerSide) {
//        this.playerSide = playerSide;
//
//        if (opponentSide.equals(playerSide)) {
//            if (opponentSide.equals(_sides[0]))
//                opponentSide = _sides[1];
//            else {
//                opponentSide = _sides[0];
//            }
//        }
//    }
//
//    public void setOpponentSide(String opponentSide) {
//        this.opponentSide = opponentSide;
//
//        if (playerSide.equals(playerSide)) {
//            if (playerSide.equals(_sides[0]))
//                playerSide = _sides[1];
//            else {
//                playerSide = _sides[0];
//            }
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_addgame, container, false);

        nicks = new ArrayList<>();
        movesList = new ArrayList<>();
        tours = new ArrayList<>();

        table = (TableLayout) view.findViewById(R.id.tblMovesListAddGame);

        gameDBHelper = new GameDBHelper(getActivity());
        tournamentDBHelper = new TournamentDBHelper(getActivity());
        userDBHelper = new UserDBHelper(getActivity());

        spinnerResult = (Spinner) view.findViewById(R.id.spinnerGameResult);
        spinnerTour = (Spinner) view.findViewById(R.id.spinnerTor);
        etBlackMove = (EditText) view.findViewById(R.id.editText3);
        etWhiteMove = (EditText) view.findViewById(R.id.etMoveWhite);
        etDate = (EditText) view.findViewById(R.id.editText8);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        etDate.setText(simpleDateFormat.format(new Date()));

        buttonAdd = (Button) view.findViewById(R.id.btnAddMoveGame);
        buttonConfirm = (Button) view.findViewById(R.id.btnConfirmGame);

        sideSpinnerPlayer = (Spinner) view.findViewById(R.id.spinnerName);
        sideSpinnerOpponent = (Spinner) view.findViewById(R.id.spinnerOpponent);
        _sides = new String[]{view.getResources().getString(R.string.sideWhite), view.getResources().getString(R.string.sideBlack)};

        getNicks();
        getTour();
        addMove();
        addGame();

        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, nicks);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, tours);
        sideSpinnerOpponent.setAdapter(adapter2);
        sideSpinnerPlayer.setAdapter(adapter2);
        spinnerTour.setAdapter(adapter);


        sideSpinnerOpponent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                setOpponentSide((String) sideSpinnerOpponent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sideSpinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                setPlayerSide((String) sideSpinnerPlayer.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(view.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        CreateTables(view);

        return view;
    }

    private void updateLabel() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void addMove() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("WTF", "WTF");
                if (etWhiteMove.getText().toString().isEmpty() && etBlackMove.getText().toString().isEmpty())
                    Toast.makeText(getActivity(), "No moves to add", Toast.LENGTH_SHORT).show();
                else {
                    if (etWhiteMove.getText().toString().isEmpty()) {
                        movesList.add("Black: " + etBlackMove.getText().toString());
                        ++blackPoints;
                        Log.i("IFS", "Pierwszy if");

                    } else {
                        movesList.add("White: " + etWhiteMove.getText().toString());
                        Log.i("IFS", "Pierwszy else");
                        ++whitePoints;
                        if (!etBlackMove.getText().toString().isEmpty()) {
                            movesList.add("Black: " + etBlackMove.getText().toString());
                            ++blackPoints;
                            Log.i("IFS", "Drugi if");
                        }
                    }
                }
                AddContent(table);
                movesList.clear();
            }

        });
    }

    private void getNicks() {
        result = userDBHelper.getAllData();
        if (result.getCount() == 0) {
            Toast.makeText(getActivity(), "No records of users in database", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {
                nicks.add(result.getString(1));
            }
        }
    }

    private void getTour() {
        result = tournamentDBHelper.getAllData();
        if (result.getCount() == 0) {
            Toast.makeText(getActivity(), "No records of tournaments in database", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {
                tours.add(result.getString(1));
            }
        }
    }

    private void addGame() {
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json = new JSONObject();
                try {
                    json.put("uniqueArrays", new JSONArray(movesList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                boolean isInserted = gameDBHelper.insertData(sideSpinnerPlayer.getSelectedItem().toString(),
                        String.valueOf(whitePoints), sideSpinnerOpponent.getSelectedItem().toString(),
                        spinnerResult.getSelectedItem().toString(), etDate.getText().toString(), json.toString(), spinnerTour.getSelectedItem().toString());
                if (isInserted) {
                    Toast.makeText(getActivity(), "Added to database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CreateTables(View view) {

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
    private void AddContent(TableLayout table) {

        TableRow row;
        TextView tv;
        Random rng = new Random();

        for (int i = 0; i < movesList.size(); i++) {
            row = new TableRow(getActivity());

            tv = new TextView(getActivity());
            tv.setText(movesList.get(i));
            row.addView(tv);

            table.addView(row);
        }
    }
}
