package com.example.ruby.loltellme.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.orm.SugarDb;

public class DatabaseContract {
    //URI data
    public static final String CONTENT_AUTHORITY = "com.example.ruby.loltellme";
    public static final String PATH = "champion";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String SCORES_TABLE = "db_champion";
    public static final class scores_table implements BaseColumns
    {
        //Table data
        public static final String ID = "champion_id";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String TAGS = "tags";

        public static Cursor buildScore(Context context){
            SugarDb sugarDb = new SugarDb(context);
            SQLiteDatabase db = sugarDb.getDB();

            return db.rawQuery("SELECT champion_id, name, image, tags FROM DB_CHAMPION WHERE free_to_play = 1", null);
        }
    }
}
