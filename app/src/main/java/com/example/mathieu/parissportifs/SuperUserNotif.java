package com.example.mathieu.parissportifs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SuperUserNotif extends Fragment {

    private Button sendNotifications;

    public static SuperUserNotif newInstance () {
        SuperUserNotif fragment = new SuperUserNotif();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_super_user_notif, container, false);

        sendNotifications = (Button) view.findViewById(R.id.sendNotifications);

        sendNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://console.firebase.google.com/project/parissportifs-d74e4/notification/compose?campaignId=8388219356442312473"));
                startActivity(intent);


            }
        });

        return view;
    }
}
