package com.example.piotr.rankingszachowy;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Piotr on 02.04.2017.
 */

public class SearchFragment extends Fragment {


    private int selectedOption = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);


        Button btnOptions = (Button)view.findViewById(R.id.btnSearchOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == null) return;
                ShowOptionsDialog(v);
            }
        });


        return view;
    }


    private void ShowOptionsDialog(View view){

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
                Toast.makeText(getActivity(), "item " + selectedOption, Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
