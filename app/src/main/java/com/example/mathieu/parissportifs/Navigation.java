package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Navigation extends AppCompatActivity {

    private BottomBar bottomBar;
    private String key;
    private ImageView imageViewSettings;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(CreateOrJoinCompetition.COMPETITION_ID);
        key = bundle.getString(CreateOrJoinCompetition.COMPETITION_ID);

        imageViewSettings = (ImageView) findViewById(R.id.imageViewSettings);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.selectTabAtPosition(1);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                Fragment selectedFragment = null;

                if (tabId == R.id.tab_homeCompetition) {
                    selectedFragment = HomeCompetition.newInstance();
                } else if (tabId == R.id.tab_competition) {
                    selectedFragment = Competition.newInstance(key);
                } else if (tabId == R.id.tab_challenge) {
                    selectedFragment = Challenge.newInstance(key);
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }
        });
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, ModifyProfile.class);
                startActivity(intent);
                finish();

            }
        });
    }


    public String getKey() {
        return key;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
