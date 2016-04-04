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

    //For Ad
    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;

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


    /**
     * Methods For Example To Add Ad
     */
    private void setupAd(){
        // Create the text view to show the level number.
        mLevelTextView = (TextView) findViewById(R.id.level);
        mLevel = START_LEVEL;

        // Create the next level button, which tries to show an interstitial when clicked.
        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
        mNextLevelButton.setEnabled(false);
        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
            }
        });

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
}
