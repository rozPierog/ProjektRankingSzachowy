package com.example.piotr.rankingszachowy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {


    private ImageButton btnHome;
    private ImageButton btnTournaments;
    private ImageButton btnAddGame;
    private ImageButton btnPlayerProfile;
    private ImageButton btnSearch;

    private ImageButton lastActiveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        RecentGamesFragment recentGamesFragment = new RecentGamesFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.add(R.id.fragmentContainer, recentGamesFragment);
        fragmentTransaction.commit();

        btnHome = (ImageButton)findViewById(R.id.btnHome);
        btnHome.setBackground(getDrawable(R.color.colorButtonActive));
        lastActiveButton = btnHome;
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                RecentGamesFragment recentGamesFragment = new RecentGamesFragment();

                transaction.replace(R.id.fragmentContainer, recentGamesFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

                lastActiveButton.setBackground((getDrawable(R.color.colorButton)));
                btnHome.setBackground(getDrawable(R.color.colorButtonActive));
                lastActiveButton = btnHome;
            }
        });

        btnTournaments = (ImageButton)findViewById(R.id.btnTournaments);
        btnTournaments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment currentTournamentsFragment = new CurrentTournamentsFragment();

                transaction.replace(R.id.fragmentContainer, currentTournamentsFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

                lastActiveButton.setBackground((getDrawable(R.color.colorButton)));
                btnTournaments.setBackground(getDrawable(R.color.colorButtonActive));
                lastActiveButton = btnTournaments;
            }
        });

        btnAddGame = (ImageButton)findViewById(R.id.btnAddGame);
        btnAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment addGameFragment = new AddGameFragment();

                transaction.replace(R.id.fragmentContainer, addGameFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

                lastActiveButton.setBackground((getDrawable(R.color.colorButton)));
                btnAddGame.setBackground(getDrawable(R.color.colorButtonActive));
                lastActiveButton = btnAddGame;
            }
        });

        btnPlayerProfile = (ImageButton)findViewById(R.id.btnProfile);
        btnPlayerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment playerProfileFragment = new PlayerProfileFragment();

                transaction.replace(R.id.fragmentContainer, playerProfileFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

                lastActiveButton.setBackground((getDrawable(R.color.colorButton)));
                btnPlayerProfile.setBackground(getDrawable(R.color.colorButtonActive));
                lastActiveButton = btnPlayerProfile;
            }
        });

        btnSearch = (ImageButton)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment searchFragment = new SearchFragment();

                transaction.replace(R.id.fragmentContainer, searchFragment);
                transaction.addToBackStack(null);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

                lastActiveButton.setBackground((getDrawable(R.color.colorButton)));
                btnSearch.setBackground(getDrawable(R.color.colorButtonActive));
                lastActiveButton = btnSearch;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
