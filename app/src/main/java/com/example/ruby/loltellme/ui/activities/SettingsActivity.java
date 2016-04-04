package com.example.ruby.loltellme.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.models.item_model.DBItem;
import com.example.ruby.loltellme.utils.Constants;
import com.example.ruby.loltellme.utils.GeneralHelper;
import com.example.ruby.loltellme.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.sp_language)
    Spinner sp_language;
    @Bind(R.id.bt_refresh)
    Button bt_refresh;

    String[] locales;
    String[] languages;

    boolean onSetup = true;
    long lastUpdateMilis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        getData();
        setupView();
    }

    private void getData(){
        lastUpdateMilis = PreferencesManager.getInstance(this).getLastUpdate() + Constants.MIN_TIME_FOR_NEXT_REFRESH;
    }

    private void setupView(){
        //TOOLBAR SETUP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        locales = getResources().getStringArray(R.array.locales);
        languages = getResources().getStringArray(R.array.languages);

        // Spinner click listener
        sp_language.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>(Arrays.asList(languages));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp_language.setAdapter(dataAdapter);
        sp_language.setSelection(PreferencesManager.getInstance(this).getLocale(), false);
    }

    @OnClick(R.id.bt_refresh)
    public void btnRefresh(){
        Calendar currentCalendar = Calendar.getInstance();
        long currentMilis = currentCalendar.getTimeInMillis();
        if(currentMilis < lastUpdateMilis) {
            Timber.d("currentCalendar=%d getLastUpdate=%d", currentMilis, lastUpdateMilis) ;
            long diff = lastUpdateMilis - currentMilis;
            int minutes = (int) (diff/60000);
            int seconds = (int) ((diff % 60000)/1000);
            Toast.makeText(this, getString(R.string.st_cannot_refresh, minutes, seconds), Toast.LENGTH_SHORT).show();
        }else{
            DBChampion.deleteAll(DBChampion.class);
            DBItem.deleteAll(DBItem.class);
            GeneralHelper.goToLoader(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!onSetup) {
            PreferencesManager.getInstance(this).saveLocale(position);
            Toast.makeText(this, getString(R.string.st_language_message, languages[position]), Toast.LENGTH_SHORT).show();
        }else{
            onSetup = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
