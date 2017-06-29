package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class SuperUserNavigation extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView imageViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_user_navigation);

        imageViewSettings = (ImageView) findViewById(R.id.imageViewSettings);

        bottomBar = (BottomBar) findViewById(R.id.bottomBarSuperUser);

        bottomBar.selectTabAtPosition(0);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                Fragment selectedFragment = null;

                if (tabId == R.id.tab_calendar) {
                    selectedFragment = SuperUserCalendar.newInstance();
                } else if (tabId == R.id.tab_notifications) {
                    selectedFragment = SuperUserNotif.newInstance();
                } else if (tabId == R.id.tab_ranking) {
                    selectedFragment = SuperUserRanking.newInstance();
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_super_user, selectedFragment);
                transaction.commit();
            }
        });
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuperUserNavigation.this, ModifyProfile.class);
                startActivity(intent);
                finish();

            }
        });
    }
}

