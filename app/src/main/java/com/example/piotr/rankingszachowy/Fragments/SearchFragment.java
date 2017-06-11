package com.example.piotr.rankingszachowy.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotr.rankingszachowy.DBHelpers.GameDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.TournamentDBHelper;
import com.example.piotr.rankingszachowy.DBHelpers.UserDBHelper;
import com.example.piotr.rankingszachowy.R;

/**
 * Created by Piotr on 02.04.2017.
 */

public class SearchFragment extends Fragment {


    private int selectedOption = 0;
    SearchView searchView;
    UserDBHelper userDBHelper;
    TournamentDBHelper tournamentDBHelper;
    GameDBHelper gameDBHelper;
    TableLayout table;
    TableRow row;
    TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        table = (TableLayout) view.findViewById(R.id.tblSearchTable);
        row = new TableRow(getActivity());

        tournamentDBHelper = new TournamentDBHelper(getActivity());
        userDBHelper = new UserDBHelper(getActivity());
        gameDBHelper = new GameDBHelper(getActivity());


        searchView = (SearchView) view.findViewById(R.id.searchView);
        Button btnOptions = (Button) view.findViewById(R.id.btnSearchOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == null) return;
                ShowOptionsDialog(v);
            }
        });
        prepareSearch();


        return view;
    }

    private void prepareSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            Cursor result;


            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("QUERY", "in submit");
                table.removeAllViews();
                switch (selectedOption) {
                    case 0:
                        result = userDBHelper.getSpecificData(query);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(2));
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(4));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(5));
                                row.addView(tv);
                                table.addView(row);

                            }
                        }
                        break;
                    case 1:
                        result = gameDBHelper.getSpecificData(query);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(2));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(4));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(5));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(7));
                                row.addView(tv);

                                table.addView(row);

                            }
                        }
                        break;
                    case 2:
                        result = tournamentDBHelper.getSpecificData(query);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);


                                table.addView(row);

                            }
                        }
                        break;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("QUERY", "in text change");
                table.removeAllViews();
                switch (selectedOption) {
                    case 0:
                        result = userDBHelper.getSpecificData(newText);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(2));
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(4));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(5));
                                row.addView(tv);
                                table.addView(row);


                            }
                        }
                        break;
                    case 1:
                        result = gameDBHelper.getSpecificData(newText);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(2));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(4));
                                row.addView(tv);
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(5));
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(7));
                                row.addView(tv);

                                table.addView(row);

                            }
                        }
                        break;
                    case 2:
                        result = tournamentDBHelper.getSpecificData(newText);
                        if (result.getCount() == 0) {
                            Toast.makeText(getActivity(), "No records in database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (result.moveToNext()) {
                                row = new TableRow(getActivity());
                                tv = new TextView(getActivity());
                                tv.setText(result.getString(1) + " ");
                                row.addView(tv);

                                tv = new TextView(getActivity());
                                tv.setText(result.getString(3));
                                row.addView(tv);


                                table.addView(row);

                            }
                        }
                        break;
                }
                return false;
            }
        });
    }


    private void ShowOptionsDialog(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(view.getResources().getString(R.string.searchOptions_btn));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setSingleChoiceItems(R.array.SearchOptions, selectedOption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedOption = which;
                Toast.makeText(getActivity(), "item " + selectedOption, Toast.LENGTH_SHORT).show();
                table.removeAllViews();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
