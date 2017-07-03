package com.example.mathieu.parissportifs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import static com.example.mathieu.parissportifs.R.id.textViewPlayerRank;


public class CompetitionListAdapter extends BaseAdapter {

    private List<CompetitionModel> competitionModelList;
    private TextView CompetitionName;
    private TextView ChampionshipName;
    private Context context;
    private LayoutInflater inflater;

    public CompetitionListAdapter(List<CompetitionModel> competitionModelList, Context context, LayoutInflater inflater) {
        this.competitionModelList = competitionModelList;
        this.context = context;
        this.inflater = inflater;
    }
    @Override
    public int getCount() {
        return competitionModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return competitionModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = this.inflater.inflate(R.layout.competitions_list_items, parent, false);
        }

        CompetitionName = (TextView) convertView.findViewById(R.id.eTextCompetitionName);

        CompetitionName.setText(competitionModelList.get(position).getCompetitionName());

        ChampionshipName = (TextView) convertView.findViewById(R.id.eTextChampionnatName);

        ChampionshipName.setText(String.valueOf(competitionModelList.get(position).getChamionshipName()));


        return convertView;
    }

    public String getmKey (int position){
        return competitionModelList.get(position).getCompetitionIdReedeemCode();
    }

}




