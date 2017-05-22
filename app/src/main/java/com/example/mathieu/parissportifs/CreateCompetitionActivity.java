package com.example.mathieu.parissportifs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CreateCompetitionActivity extends AppCompatActivity {

    private Spinner ChampionShipSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_competition);

        ChampionShipSelector = (Spinner) findViewById(R.id.championship_spinner);

        addItemOnChampionShipSelector();
    }

    public void addItemOnChampionShipSelector() {


        List<String> list = new ArrayList<String>();
        list.add("Ligue 1");
        list.add("Barclays League");
        list.add("Bundesliga");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChampionShipSelector.setAdapter(dataAdapter);
    }
}
