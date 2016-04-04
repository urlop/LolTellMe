package com.example.ruby.loltellme.utils.retrofit;

import com.example.ruby.loltellme.models.champion_model.ChampionNonStatic;
import com.example.ruby.loltellme.models.champion_model.ResponseNonStaticChampion;
import com.example.ruby.loltellme.models.item_model.ResponseItem;
import com.example.ruby.loltellme.models.champion_model.ResponseChampion;
import com.example.ruby.loltellme.models.map_model.ResponseMap;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface EverlanceServices {

    @GET(Urls.GET_CHAMPIONS)
    Call<ResponseChampion> getChampions(@Query("locale") String locale);

    @GET(Urls.GET_ITEMS)
    Call<ResponseItem> getItems(@Query("locale") String locale);

    @GET(Urls.GET_MAPS)
    Call<ResponseMap> getMaps(@Query("locale") String locale);

    @GET(Urls.GET_NON_STATIC_CHAMPIONS)
    Call<ResponseNonStaticChampion> getNonStaticChampions(@Path("region") String region);
}
