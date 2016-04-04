/***
 * Copyright (c) 2008-2012 CommonsWare, LLC
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 * by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * <p/>
 * From _The Busy Coder's Guide to Advanced Android Development_
 * http://commonsware.com/AdvAndroid
 */

package com.example.ruby.loltellme.utils.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.utils.Constants;
import com.example.ruby.loltellme.utils.DatabaseContract;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import timber.log.Timber;

public class LoremViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static ArrayList<Champion> items = new ArrayList<>();
    private Context ctxt = null;
    private int appWidgetId;

    public LoremViewsFactory(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        Cursor cursor = DatabaseContract.scores_table.buildScore(ctxt);
        Timber.d("cursor=%s", cursor);
        Timber.d("cursor.columnNames=%s", cursor.getColumnNames());
        int id;
        String tags, image, name;

        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("CHAMPION_ID"));
            tags = cursor.getString(cursor.getColumnIndex("TAGS"));
            image = cursor.getString(cursor.getColumnIndex("IMAGE"));
            name = cursor.getString(cursor.getColumnIndex("NAME"));

            items.add(new DBChampion(id, tags, image, name).parseChampion());
        }
        cursor.close();
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return (items.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews row = new RemoteViews(ctxt.getPackageName(), R.layout.widget_row);
        final Champion item = items.get(position);

        row.setTextViewText(R.id.home_name, item.getName());
        try {
            Bitmap b = Picasso.with(ctxt).load(item.getImage().getFull(Image.IMAGE_TYPE_CHAMPION)).get();
            row.setImageViewBitmap(R.id.home_crest, b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i = new Intent();
        Bundle extras = new Bundle();

        extras.putInt(Constants.EXTRA_CHAMPION_ID, item.getId());

        i.putExtras(extras);
        row.setOnClickFillInIntent(R.id.ll_container, i);

        return (row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return (null);
    }

    @Override
    public int getViewTypeCount() {
        return (1);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public boolean hasStableIds() {
        return (true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
    }
}