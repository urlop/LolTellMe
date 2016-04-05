package com.example.ruby.loltellme.ui.activities;

import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.utils.GPSTracker;
import com.example.ruby.loltellme.utils.GeneralHelper;
import com.example.ruby.loltellme.utils.PreferencesManager;
import com.example.ruby.loltellme.utils.ServicesHelper;
import com.example.ruby.loltellme.utils.retrofit.EverlanceServices;
import com.example.ruby.loltellme.utils.retrofit.RequestManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashLoader extends AppCompatActivity {

    //For WS
    EverlanceServices service;

    @Bind(R.id.pb_loader)
    ProgressBar pb_loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //setupAd();

        loadDataIfNecessary();
    }

    private void loadDataIfNecessary(){
        pb_loader.setMax(100);
        pb_loader.setProgress(0);

        //DBChampion.deleteAll(DBChampion.class);
        if (DBChampion.count(DBChampion.class) > 0 && DBItem.count(DBItem.class) > 0){
            GeneralHelper.goToMain(this);
        } else {
            GPSTracker gps = new GPSTracker(this);

            PreferencesManager.getInstance(this).saveLatitude((float) gps.getLatitude());
            PreferencesManager.getInstance(this).saveLongitude((float) gps.getLongitude());

            service = RequestManager.getDefault();
            ServicesHelper.downloadAllData(this, service, pb_loader);
        }
    }
}
