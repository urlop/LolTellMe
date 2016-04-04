package com.example.ruby.loltellme.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.location.Location;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.models.champion_model.ChampionNonStatic;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.models.champion_model.ResponseChampion;
import com.example.ruby.loltellme.models.champion_model.ResponseNonStaticChampion;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.models.item_model.Item;
import com.example.ruby.loltellme.models.item_model.ResponseItem;
import com.example.ruby.loltellme.models.map_model.Map;
import com.example.ruby.loltellme.models.map_model.ResponseMap;
import com.example.ruby.loltellme.utils.retrofit.APIError;
import com.example.ruby.loltellme.utils.retrofit.CustomCallback;
import com.example.ruby.loltellme.utils.retrofit.EverlanceServices;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * Created by Ruby on 22/03/2016.
 */
public class ServicesHelper {

    static ObjectAnimator animation;

    public static void downloadAllData(Context context, EverlanceServices service, ProgressBar progressBar){
        getItems(context, service, progressBar);
    }

    public static void getMaps(final Context context, EverlanceServices service){
        String locale = context.getResources().getStringArray(R.array.locales)[PreferencesManager.getInstance(context).getLocale()];

        Call<ResponseMap> call = service.getMaps(locale);
        call.enqueue(new CustomCallback<ResponseMap>(context, animation) {
            @Override
            public void onResponse(Response<ResponseMap> response, Retrofit retrofit) {
                if (response != null && !response.isSuccess() && response.errorBody() != null) {
                    Converter<ResponseBody, APIError> errorConverter =
                            retrofit.responseConverter(APIError.class, new Annotation[0]);
                    String errorMessage = "ERROR";
                    try {
                        APIError error;
                        error = errorConverter.convert(response.errorBody());
                        errorMessage = error.getStatus().getMessage();
                    } catch (IOException e) {
                        Timber.i("getMaps with no errors");
                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    return;
                }

                java.util.Map<String, Map> map = response.body().getData();
                List<com.example.ruby.loltellme.models.map_model.Map> maps = Collections.list(Collections.enumeration(map.values()));

                com.example.ruby.loltellme.models.map_model.Map mapItem = maps.get(0);
                String string = mapItem.getMapId() + "\n";
                string = string + mapItem.getMapName() + "\n";

                //tv_text.setText(string);
            }
        });
    }

    public static void getItems(final Context context, final EverlanceServices service, final ProgressBar progressBar){
        String locale = context.getResources().getStringArray(R.array.locales)[PreferencesManager.getInstance(context).getLocale()];
        updateProgress(progressBar, 33, 4000);

        Call<ResponseItem> call = service.getItems(locale);
        call.enqueue(new CustomCallback<ResponseItem>(context, animation) {
            @Override
            public void onResponse(Response<ResponseItem> response, Retrofit retrofit) {
                if (response != null && !response.isSuccess() && response.errorBody() != null) {
                    Converter<ResponseBody, APIError> errorConverter =
                            retrofit.responseConverter(APIError.class, new Annotation[0]);
                    String errorMessage = "ERROR";
                    try {
                        APIError error;
                        error = errorConverter.convert(response.errorBody());
                        errorMessage = error.getStatus().getMessage();
                    } catch (IOException e) {
                        Timber.i("getItems with no errors");
                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    return;
                }

                java.util.Map<String, Item> map = response.body().getData();
                List<Item> items = Collections.list(Collections.enumeration(map.values()));

                int currentProgress = 0;
                float progress = progressBar.getProgress();
                for (Item i : items) {
                    DBItem dbItem = i.parseItem();
                    dbItem.save();
                }

                getChampions(context, service, progressBar);
            }

            @Override
            public void onFailure(Throwable t) {
                animation.cancel();
                super.onFailure(t);
            }
        });
    }

    public static void getChampions(final Context context, final EverlanceServices service, final ProgressBar progressBar){
        String locale = context.getResources().getStringArray(R.array.locales)[PreferencesManager.getInstance(context).getLocale()];
        updateProgress(progressBar, 66, 9000);

        Call<ResponseChampion> call = service.getChampions(locale);
        call.enqueue(new CustomCallback<ResponseChampion>(context, animation) {
            @Override
            public void onResponse(Response<ResponseChampion> response, Retrofit retrofit) {
                String str = "";
                try {
                    str = response.errorBody().string();
                    Timber.e(str);
                } catch (Exception e) {
                    Timber.i("getChampions with no errors");
                }

                java.util.Map<String, Champion> map = response.body().getData();
                List<Champion> champions = Collections.list(Collections.enumeration(map.values()));

                DBChampion.deleteAll(DBChampion.class);

                float progress = progressBar.getProgress();
                for (Champion c : champions) {
                    DBChampion dbChampion = c.parseChampion();
                    dbChampion.save();
                    progress = progress + 0.256f;
                    progressBar.setProgress((int) progress);
                }

                getNonStaticChampions(context, service, progressBar);
            }
        });
    }

    public static void getNonStaticChampions(final Context context, EverlanceServices service, final ProgressBar progressBar) {
        updateProgress(progressBar, 100, 1000);

        String region = GeneralHelper.getRegion(PreferencesManager.getInstance(context).getLatitude(), PreferencesManager.getInstance(context).getLongitude());
        Call<ResponseNonStaticChampion> call = service.getNonStaticChampions(region);
        call.enqueue(new CustomCallback<ResponseNonStaticChampion>(context, animation) {
            @Override
            public void onResponse(Response<ResponseNonStaticChampion> response, Retrofit retrofit) {
                String str = "";
                try {
                    str = response.errorBody().string();
                    Timber.e(str);
                } catch (Exception e) {
                    Timber.i("getNonStaticChampions with no errors");
                }

                List<ChampionNonStatic> champions = response.body().getChampions();

                for (ChampionNonStatic c : champions) {
                    Timber.d("champion id=%s active=%s free=%s", c.getId(), c.isActive(), c.isFreeToPlay());
                    DBChampion dbChampion = DBChampion.find(DBChampion.class, "champion_id = ?", String.valueOf(c.getId())).get(0);
                    dbChampion.setActive(c.isActive());
                    dbChampion.setFreeToPlay(c.isFreeToPlay());
                    dbChampion.save();
                }

                PreferencesManager.getInstance(context).saveLastUpdate(Calendar.getInstance().getTimeInMillis());
                Timber.d("lastRefresh=%d", PreferencesManager.getInstance(context).getLastUpdate());
                GeneralHelper.goToMain(context);
            }
        });
    }

    private static void updateProgress(ProgressBar progressBar, int progress, int time){
        animation = ObjectAnimator.ofInt(progressBar, "progress", progress);
        animation.setDuration(time);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
}
