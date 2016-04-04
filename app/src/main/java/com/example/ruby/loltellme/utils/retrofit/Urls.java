package com.example.ruby.loltellme.utils.retrofit;


import com.example.ruby.loltellme.utils.Constants;

public interface Urls {
    String GET_CHAMPIONS = "static-data/lan/v1.2/champion?dataById=true&champData=allytips,enemytips,image,info,partype,passive,recommended,skins,spells,stats,tags&api_key=" + Constants.API_KEY;
    String GET_ITEMS = "static-data/lan/v1.2/item?itemListData=depth,from,gold,groups,image,into,maps,requiredChampion,specialRecipe,stats,tags,tree&api_key=" + Constants.API_KEY;
    String GET_MAPS = "static-data/lan/v1.2/map?api_key=" + Constants.API_KEY;
    String GET_NON_STATIC_CHAMPIONS = "https://{region}.api.pvp.net/api/lol/{region}/v1.2/champion?api_key=" + Constants.API_KEY;

    String IMAGE_CHAMPION_SQUARE = "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/champion/";
    String IMAGE_CHAMPION_SPLASH = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
    String IMAGE_CHAMPION_SPLASH_END = "_0.jpg";
    String IMAGE_SPELL = "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/spell/";
    String IMAGE_ITEM = "http://ddragon.leagueoflegends.com/cdn/6.6.1/img/item/";
}
