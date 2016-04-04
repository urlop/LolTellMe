package com.example.ruby.loltellme.ui.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.ui.adapter.ChampionsAdapter;
import com.example.ruby.loltellme.utils.Constants;
import com.example.ruby.loltellme.utils.GeneralHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt_all_champions)
    Button bt_all_champions;
    @Bind(R.id.rv_list_current)
    RecyclerView rv_list_current;
    @Bind(R.id.rv_list_disabled)
    RecyclerView rv_list_disabled;
    @Bind(R.id.adView)
    AdView mAdView;

    List<Champion> currentChampionsList = new ArrayList<>();
    ChampionsAdapter currentAdapter;
    List<Champion> disabledChampionsList = new ArrayList<>();
    ChampionsAdapter disabledAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupView();
        getData();
        setupList();
    }

    private void setupView(){
        GeneralHelper.addAdMob(mAdView);

        rv_list_current.setHasFixedSize(true);
        rv_list_current.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_list_disabled.setHasFixedSize(true);
        rv_list_disabled.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getData(){
        List<DBChampion> champions = DBChampion.find(DBChampion.class, "free_to_play = ?", "1");
        for (DBChampion dbChampion : champions){
            currentChampionsList.add(dbChampion.parseImageChampion());
        }

        champions = DBChampion.find(DBChampion.class, "active = ?", "0");
        for (DBChampion dbChampion : champions){
            disabledChampionsList.add(dbChampion.parseImageChampion());
        }
    }

    private void setupList(){
        currentAdapter = new ChampionsAdapter(this, currentChampionsList);
        rv_list_current.setAdapter(currentAdapter);

        disabledAdapter = new ChampionsAdapter(this, disabledChampionsList);
        rv_list_disabled.setAdapter(disabledAdapter);
    }

    @OnClick(R.id.bt_all_champions)
    public void goToChampions(){
        Intent intent = new Intent(MainActivity.this, ChampionsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
