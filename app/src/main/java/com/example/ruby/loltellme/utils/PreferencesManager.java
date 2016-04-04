package com.example.ruby.loltellme.utils;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class for managing everything related to SharedPreferences
 */
public class PreferencesManager {

    private static PreferencesManager self;
    private final SharedPreferences mPreferences;
    private static final String PREFERENCES_NAME = "LollTellMe";
    private static final String LOCALE_INDICATOR = "localeIndicator";
    private static final String TIME_LAST_UPDATE = "lastUpdate";
    private static final String LOCATION_LATITUDE = "locationLatitude";
    private static final String LOCATION_LONGITUDE = "locationLongitude";

    private Context context;

    /**
     * Sets SharedPreferences' value
     *
     * @param context Environment data where manager was called
     * @see Context#getSharedPreferences(String, int)
     */
    private PreferencesManager(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Gets instance of the manager for better performance
     *
     * @param context Environment data where manager was called
     * @return instance of shared preferences manager
     */
    public static PreferencesManager getInstance(Context context) {
        if (self == null) {
            self = new PreferencesManager(context);
        }
        return self;
    }

    public void saveLocale(int localeIndicator) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LOCALE_INDICATOR, localeIndicator);
        editor.apply();
    }

    public int getLocale() {
        return mPreferences.getInt(LOCALE_INDICATOR, 0);
    }

    public void saveLastUpdate(long time) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(TIME_LAST_UPDATE, time);
        editor.apply();
    }

    public long getLastUpdate() {
        return mPreferences.getLong(TIME_LAST_UPDATE, 0);
    }

    public void saveLatitude(float latitude) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(LOCATION_LATITUDE, latitude);
        editor.apply();
    }

    public float getLatitude() {
        return mPreferences.getFloat(LOCATION_LATITUDE, 0f);
    }

    public void saveLongitude(float longitude) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(LOCATION_LONGITUDE, longitude);
        editor.apply();
    }

    public float getLongitude() {
        return mPreferences.getFloat(LOCATION_LONGITUDE, 0f);
    }
}
