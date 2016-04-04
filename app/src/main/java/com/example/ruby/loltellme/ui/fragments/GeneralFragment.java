package com.example.ruby.loltellme.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Spell;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.models.item_model.Item;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.example.ruby.loltellme.ui.adapter.ItemsAdapter;
import com.example.ruby.loltellme.ui.adapter.SpellsAdapter;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends Fragment {

    @Bind(R.id.tv_role)
    TextView tv_role;
    @Bind(R.id.rv_list_items)
    RecyclerView rv_list_items;
    @Bind(R.id.rv_list_spells)
    RecyclerView rv_list_spells;

    List<Spell> spellsList = new ArrayList<>();
    SpellsAdapter spellsAdapter;
    List<Item> itemsList = new ArrayList<>();
    ItemsAdapter itemsAdapter;

    private static GeneralFragment instance;
    private ChampionDetailActivity activity;

    public static GeneralFragment getInstance(){
        if (instance == null){
            instance = new GeneralFragment();
            Bundle args = new Bundle();
            instance.setArguments(args);
        }
        return instance;
    }

    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public GeneralFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        ButterKnife.bind(this, view);

        getData();
        setupView();
        setupList();

        return view;
    }

    private void setupView(){
        tv_role.setText(Joiner.on(", ").join(activity.getChampion().getTags()));

        rv_list_spells.setHasFixedSize(true);
        rv_list_spells.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rv_list_items.setHasFixedSize(true);
        rv_list_items.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void getData(){
        spellsList = activity.getChampion().getSpells();
        itemsList = activity.getChampion().getItems();
    }

    private void setupList(){
        spellsAdapter = new SpellsAdapter(getContext(), spellsList);
        rv_list_spells.setAdapter(spellsAdapter);

        itemsAdapter = new ItemsAdapter(getContext(), itemsList);
        rv_list_items.setAdapter(itemsAdapter);
    }
}
