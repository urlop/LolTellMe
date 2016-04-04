package com.example.ruby.loltellme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.ruby.loltellme.ui.activities.MainActivity;
import com.example.ruby.loltellme.ui.activities.SplashLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Ruby on 16/03/2016.
 */
public class GeneralHelper {
    private static final int MAXIMUM_BASIC_SPEED = 355;
    private static final int MINIMUM_BASIC_SPEED = 325;
    private static final int MAXIMUM_BASIC_SPEED_RANGE = MAXIMUM_BASIC_SPEED - MINIMUM_BASIC_SPEED;

    public static int getSpeedRank(double speed) {
        return (int) ((speed - MINIMUM_BASIC_SPEED) * 10 / MAXIMUM_BASIC_SPEED_RANGE);
    }

    public static void goToMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void goToLoader(Context context) {
        Intent intent = new Intent(context, SplashLoader.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void addAdMob(AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(Constants.APP_HASH)
                .build();
        mAdView.loadAd(adRequest);
    }

    public static String getRegion(float latitude, float longitude) {
        try {
            if ((latitude <= -57.470870 || longitude >= -33.124952) && (latitude >= 25.747530 || longitude <= -113.034081)) {
                return "na";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "lan";
    }
}
