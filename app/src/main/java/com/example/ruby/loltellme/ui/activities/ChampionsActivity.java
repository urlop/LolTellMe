package com.example.ruby.loltellme.ui.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.ui.adapter.ChampionsAdapter;
import com.example.ruby.loltellme.utils.GeneralHelper;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    @Bind(R.id.rv_list_items)
    RecyclerView rv_list;
    @Bind(R.id.adView)
    AdView mAdView;

    List<Champion> list = new ArrayList<>();
    ChampionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        ButterKnife.bind(this);

        setupView();
        getChampions();
        setupList();
    }

    private void setupView(){
        GeneralHelper.addAdMob(mAdView);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void setupList(){
        adapter = new ChampionsAdapter(this, list);
        rv_list.setAdapter(adapter);
    }

    private void getChampions(){
        Iterator iterator = DBChampion.findAll(DBChampion.class);
        while (iterator.hasNext()) {
            DBChampion dbChampion = (DBChampion) iterator.next();
            list.add(dbChampion.parseImageChampion());
        }
        Collections.sort(list);
    }

    private List<Champion> filter(List<Champion> models, String query) {
        query = query.toLowerCase();

        final List<Champion> filteredModelList = new ArrayList<>();
        for (Champion model : models) {
            if (model.getName().toLowerCase().contains(query)) {
                filteredModelList.add(model);
            }
            for (String tag : model.getTags()){
                if (tag.toLowerCase().contains(query) && !filteredModelList.contains(model)) {
                    filteredModelList.add(model);
                }
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_champions, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        SearchView.SearchAutoComplete searchViewTextArea = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        searchViewTextArea.setHint(getString(R.string.menu_search));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Champion> filteredModelList = filter(list, query);
        adapter.animateTo(filteredModelList);
        rv_list.scrollToPosition(0);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
}
