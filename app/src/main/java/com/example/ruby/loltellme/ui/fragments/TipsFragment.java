package com.example.ruby.loltellme.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.example.ruby.loltellme.ui.activities.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipsFragment extends Fragment {

    @Bind(R.id.tv_ally_tips)
    TextView tv_ally_tips;
    @Bind(R.id.tv_enemy_tips)
    TextView tv_enemy_tips;

    private static TipsFragment instance;
    private ChampionDetailActivity activity;

    public static TipsFragment getInstance(){
        if (instance == null){
            instance = new TipsFragment();
            Bundle args = new Bundle();
            instance.setArguments(args);
        }
        return instance;
    }

    public TipsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ChampionDetailActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    private void setupView(){
        String allytips = "";
        String enemytips = "";

        for (String tip : activity.getChampion().getAllytips()){
            allytips = allytips + "*" + tip + "\n";
        }
        for (String tip : activity.getChampion().getEnemytips()){
            enemytips = enemytips + "*" + tip + "\n";
        }
        tv_ally_tips.setText(allytips);
        tv_enemy_tips.setText(enemytips);
    }

}
