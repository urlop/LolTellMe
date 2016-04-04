package com.example.ruby.loltellme.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.orm.SugarDb;

public class MyContentProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.example.ruby.loltellme.MyContentProvider";
    public static final String PATH_CHAMPION = "champion";
    public static final String CONTENT_TYPE_CHAMPION = PROVIDER_NAME + "/" + PATH_CHAMPION;
    static final int CHAMPION = 10;

    public static Uri CONTENT_URI_CHAMPION = Uri.parse("content://" + CONTENT_TYPE_CHAMPION);

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private static final SQLiteQueryBuilder myChampionsQueryBuilder;
    static{
        myChampionsQueryBuilder = new SQLiteQueryBuilder();
        myChampionsQueryBuilder.setTables(PATH_CHAMPION);
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PROVIDER_NAME;
        matcher.addURI(authority, "/"+PATH_CHAMPION, CHAMPION);
        return matcher;
    }

    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);

        switch (match) {
            case CHAMPION:
                return CONTENT_TYPE_CHAMPION;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SugarDb sugarDb = new SugarDb(getContext());
        SQLiteDatabase db = sugarDb.getDB();

        Cursor cursor;
        Log.d("Uri","champion " + uri.toString());
        if(uriMatcher.match(uri) == CHAMPION) {
            Log.d("CHAMPION","champion");
            //cursor = db.rawQuery("SELECT * FROM DB_CHAMPION WHERE free_to_play = ?",new String[]{"true"});
            cursor = db.query(
                    DatabaseContract.SCORES_TABLE,
                    projection, null, null, null, null, sortOrder);
        }else {
            cursor = null;
        }

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}