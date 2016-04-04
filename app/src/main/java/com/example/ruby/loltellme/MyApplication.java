package com.example.ruby.loltellme;

import android.app.Application;

import com.orm.SugarContext;

import timber.log.Timber;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*Crashlytics crashlyticsKit = new Crashlytics.Builder().core(
                new CrashlyticsCore.Builder().disabled(Constants.CRASHLYTICS_DISABLED).build()
        ).build();
        Fabric.with(this, crashlyticsKit);

        if (Constants.DEBUG_LOG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashlyticsLogTree());
            Timber.plant(new LogentriesTree(getApplicationContext()));
        }*/

        Timber.plant(new Timber.DebugTree());
        SugarContext.init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
