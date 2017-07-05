package com.example.mathieu.parissportifs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;


public class Competition extends Fragment {

    private HorizontalCalendar horizontalCalendar;
    private ListView mGameListView;
    private DatabaseReference mDatabaseGameRef;
    private String mCompetitionId;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String GameId;
    private TextView betScoreAway, betScoreHome, textViewTag;
    int ScoreBetAway;
    int ScoreBetHome;
    private GameListAdapter mGameListAdapter;

    public static Competition newInstance (String competitonId) {
        Bundle bundle = new Bundle();
        bundle.putString(CreateOrJoinCompetition.COMPETITION_ID, competitonId);
        Competition fragment = new Competition();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCompetitionId = getArguments().getString(CreateOrJoinCompetition.COMPETITION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_competition, container, false);

        mGameListView = (ListView) view.findViewById(R.id.gameListUser);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();




        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        final Calendar defaultDate = Calendar.getInstance();
        defaultDate.add(Calendar.MONTH, -1);
        defaultDate.add(Calendar.DAY_OF_WEEK, +5);



         horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarViewUser)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .dayNameFormat("EEE")
                .dayNumberFormat("dd")
                .monthFormat("MMM")
                .showDayName(true)
                .showMonthName(true)
                .defaultSelectedDate(defaultDate.getTime())
                .textColor(Color.LTGRAY, Color.WHITE)
                .selectedDateBackground(Color.TRANSPARENT)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {

                DateFormat df = new SimpleDateFormat("yyMMdd");
                String reportDate = df.format(date);

                mDatabaseGameRef = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_GAMES).child(reportDate);

                 mGameListAdapter = new GameListAdapter(mDatabaseGameRef,getActivity(), R.layout.game_list_items, mCompetitionId, user.getUid()); // APPELLE L'ADAPTER

                mGameListView.setAdapter(mGameListAdapter); //FUSION LIST ET ADAPTER


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GetBet(mCompetitionId, user.getUid(), mGameListAdapter);

                    }
                }, 1000);



                mGameListAdapter.notifyDataSetChanged();





            }
        });



        mGameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                NewGame newGame = (NewGame) parent.getItemAtPosition(position);

                if(newGame.getmStatus().equals("OUVERT")){
                    Intent i = new Intent(getActivity(), BetGame.class);
                    i.putExtra("newGame", newGame);
                    i.putExtra(CreateOrJoinCompetition.COMPETITION_ID, mCompetitionId);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), "Tu ne peux plus pronostiquer cette rencontre !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        horizontalCalendar.goToday(true);

        return view;
    }

    public void GetBet(final String GetCompetitionId, final String GetUserId , GameListAdapter adapter){





        for (int i = 0; i < adapter.getCount(); i++){

             View view = adapter.getView(i, null, mGameListView);

            textViewTag = (TextView) view.findViewById(R.id.textViewTag);
             GameId = textViewTag.getText().toString();

            betScoreHome = (TextView) view.findViewById(R.id.textViewbetScoreHome);

            betScoreAway = (TextView) view.findViewById(R.id.textViewbetScoreAway);

            DatabaseReference mUserRef = FirebaseDatabase.getInstance().getReference(Constants.COMPET).child(GetCompetitionId).child("membersMap")
                    .child(GetUserId).child("usersBets").child(GameId);

            mUserRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    BetGameModel userBetOnCurrentMatch = dataSnapshot.getValue(BetGameModel.class);

                    if (userBetOnCurrentMatch != null && userBetOnCurrentMatch.getmGameId().equals(GameId)) {

                        ScoreBetAway = userBetOnCurrentMatch.getmAwayScore();
                        ScoreBetHome = userBetOnCurrentMatch.getmHomeScore();

                    } else {
                        ScoreBetAway = 0;
                        ScoreBetHome = 0;

                    }

                    betScoreAway.setText(String.valueOf(ScoreBetAway));
                    betScoreHome.setText(String.valueOf(ScoreBetHome));


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }





        }
    }

