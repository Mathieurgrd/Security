package com.example.mathieu.parissportifs;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apprenti on 26/06/17.
 */

public class SwitchLogoModel {

    public static  void switchLogo (TextView homeTeam, ImageView imageViewHomeTeam, TextView awayTeam, ImageView imageViewAwayTeam) {

        String homeTeamName = homeTeam.getText().toString().trim();
        String awayTeamName = awayTeam.getText().toString().trim();


        switch (homeTeamName) {

            case (Constants.LILLE):
                imageViewHomeTeam.setImageResource(R.drawable.lille);
                break;

            case (Constants.EA_GUINGAMP):
                imageViewHomeTeam.setImageResource(R.drawable.guingamp);
                break;

            case (Constants.MONACO):
                imageViewHomeTeam.setImageResource(R.drawable.monaco);
                break;

            case (Constants.ANGERS_SCO):
                imageViewHomeTeam.setImageResource(R.drawable.angers);
                break;

            case (Constants.GIRONDINS_BORDEAUX):
                imageViewHomeTeam.setImageResource(R.drawable.bordeaux);
                break;

            case (Constants.CAEN):
                imageViewHomeTeam.setImageResource(R.drawable.caen);
                break;

            case (Constants.DIJON_FC):
                imageViewHomeTeam.setImageResource(R.drawable.dijon);
                break;

            case (Constants.LYON):
                imageViewHomeTeam.setImageResource(R.drawable.lyon);
                break;

            case (Constants.MARSEILLE):
                imageViewHomeTeam.setImageResource(R.drawable.om);
                break;

            case (Constants.METZ):
                imageViewHomeTeam.setImageResource(R.drawable.metz);
                break;

            case (Constants.MONTPELIER):
                imageViewHomeTeam.setImageResource(R.drawable.montpelier);
                break;

            case (Constants.NANTES):
                imageViewHomeTeam.setImageResource(R.drawable.nantes);
                break;

            case (Constants.NICE):
                imageViewHomeTeam.setImageResource(R.drawable.nice);
                break;

            case (Constants.PSG):
                imageViewHomeTeam.setImageResource(R.drawable.psg);
                break;

            case (Constants.RENNES):
                imageViewHomeTeam.setImageResource(R.drawable.renne);
                break;

            case (Constants.SAINT_ETIENNE):
                imageViewHomeTeam.setImageResource(R.drawable.saint_etienne);
                break;

            case (Constants.STRASBOURG):
                imageViewHomeTeam.setImageResource(R.drawable.strasbourg);
                break;

            case (Constants.TFC):
                imageViewHomeTeam.setImageResource(R.drawable.toulouse);
                break;

            case (Constants.TROYES):
                imageViewHomeTeam.setImageResource(R.drawable.troyes);
                break;

            case (Constants.AMIENS):
                imageViewHomeTeam.setImageResource(R.drawable.amiens);
                break;

            default:
                break;
        }


        switch (awayTeamName) {

            case  (Constants.LILLE):
                imageViewAwayTeam.setImageResource(R.drawable.lille);
                break;

            case (Constants.EA_GUINGAMP):
                imageViewAwayTeam.setImageResource(R.drawable.guingamp);
                break;

            case (Constants.MONACO):
                imageViewAwayTeam.setImageResource(R.drawable.monaco);
                break;

            case (Constants.ANGERS_SCO):
                imageViewAwayTeam.setImageResource(R.drawable.angers);
                break;

            case (Constants.GIRONDINS_BORDEAUX):
                imageViewAwayTeam.setImageResource(R.drawable.bordeaux);
                break;

            case (Constants.CAEN):
                imageViewAwayTeam.setImageResource(R.drawable.caen);
                break;

            case (Constants.DIJON_FC):
                imageViewAwayTeam.setImageResource(R.drawable.dijon);
                break;

            case (Constants.LYON):
                imageViewAwayTeam.setImageResource(R.drawable.lyon);
                break;

            case (Constants.MARSEILLE):
                imageViewAwayTeam.setImageResource(R.drawable.om);
                break;

            case (Constants.METZ):
                imageViewAwayTeam.setImageResource(R.drawable.metz);
                break;

            case (Constants.MONTPELIER):
                imageViewAwayTeam.setImageResource(R.drawable.montpelier);
                break;

            case (Constants.NANTES):
                imageViewAwayTeam.setImageResource(R.drawable.nantes);
                break;

            case (Constants.NICE):
                imageViewAwayTeam.setImageResource(R.drawable.nice);
                break;

            case (Constants.PSG):
                imageViewAwayTeam.setImageResource(R.drawable.psg);
                break;

            case (Constants.RENNES):
                imageViewAwayTeam.setImageResource(R.drawable.renne);
                break;

            case (Constants.SAINT_ETIENNE):
                imageViewAwayTeam.setImageResource(R.drawable.saint_etienne);
                break;

            case (Constants.STRASBOURG):
                imageViewAwayTeam.setImageResource(R.drawable.strasbourg);
                break;

            case (Constants.TFC):
                imageViewAwayTeam.setImageResource(R.drawable.toulouse);
                break;

            case (Constants.TROYES):
                imageViewAwayTeam.setImageResource(R.drawable.troyes);
                break;

            case (Constants.AMIENS):
                imageViewAwayTeam.setImageResource(R.drawable.amiens);
                break;

            default:
                break;
        }

    }
}
