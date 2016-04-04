package com.example.ruby.loltellme.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Stat;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.example.ruby.loltellme.utils.GeneralHelper;
import com.example.ruby.loltellme.utils.MyMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class StatsFragment extends Fragment {

    @Bind(R.id.chart1)
    RadarChart mChart;

    @Bind(R.id.tv_health) TextView tv_health;
    @Bind(R.id.tv_health_regen) TextView tv_health_regen;
    @Bind(R.id.tv_mana) TextView tv_mana;
    @Bind(R.id.tv_mana_regen) TextView tv_mana_regen;
    @Bind(R.id.tv_attack_damage) TextView tv_attack_damage;
    @Bind(R.id.tv_attack_speed) TextView tv_attack_speed;
    @Bind(R.id.tv_attack_range) TextView tv_attack_range;
    @Bind(R.id.tv_armor) TextView tv_armor;
    @Bind(R.id.tv_magic_resist) TextView tv_magic_resist;
    @Bind(R.id.tv_movement) TextView tv_movement;

    private String[] mParties;

    private static StatsFragment instance;
    private ChampionDetailActivity activity;

    public static StatsFragment getInstance(){
        if (instance == null){
            instance = new StatsFragment();
            Bundle args = new Bundle();
            instance.setArguments(args);
        }
        return instance;
    }

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ChampionDetailActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    private void setupView(){
        mParties = getResources().getStringArray(R.array.stats_parties);

        mChart.setDescription("");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.75f);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);

        setChartData();
        setAllData();

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(12f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(6, false);
        yAxis.setTextSize(12f);
        yAxis.setAxisMinValue(0f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

        mChart.getYAxis().setAxisMaxValue(10f);
    }

    public void setChartData() {
        //mChart.spin(2000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption.EaseInCubic);
        ArrayList<Entry> yVals1 = new ArrayList<>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        yVals1.add(new Entry(activity.getChampion().getInfo().getDefense(),0));
        yVals1.add(new Entry(activity.getChampion().getInfo().getMagic(),1));
        yVals1.add(new Entry(activity.getChampion().getInfo().getAttack(),2));
        yVals1.add(new Entry(activity.getChampion().getInfo().getDifficulty(),3));
        yVals1.add(new Entry(GeneralHelper.getSpeedRank(activity.getChampion().getStats().getMovespeed()),4));

        Timber.d("getDefense %d", activity.getChampion().getInfo().getDefense());
        Timber.d("getMagic %d", activity.getChampion().getInfo().getMagic());
        Timber.d("getAttack %d", activity.getChampion().getInfo().getAttack());
        Timber.d("getDifficulty %d", activity.getChampion().getInfo().getDifficulty());
        Timber.d("getSpeed %d", GeneralHelper.getSpeedRank(activity.getChampion().getStats().getMovespeed()));

        ArrayList<String> xVals = new ArrayList<>();
        xVals.addAll(Arrays.asList(mParties));

        RadarDataSet set1 = new RadarDataSet(yVals1, "Basic Stat");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[3]);
        set1.setFillColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(xVals, sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);

        mChart.setData(data);
        mChart.setRotationEnabled(false);

        mChart.invalidate();
    }

    public void setAllData(){
        Stat stat = activity.getChampion().getStats();
        tv_health.setText(getString(R.string.s_stat_format, stat.getHp(), stat.getHpperlevel()));
        tv_health_regen.setText(getString(R.string.s_stat_format, stat.getHpregen(), stat.getHpregenperlevel()));
        tv_mana.setText(getString(R.string.s_stat_format, stat.getMp(), stat.getMpperlevel()));
        tv_mana_regen.setText(getString(R.string.s_stat_format, stat.getMpregen(), stat.getMpregenperlevel()));
        tv_attack_damage.setText(getString(R.string.s_stat_format, stat.getAttackdamage(), stat.getAttackdamageperlevel()));
        tv_attack_speed.setText(getString(R.string.s_stat_format_percent, stat.getAttackspeed(), stat.getAttackspeedperlevel()));
        tv_attack_range.setText(String.valueOf(stat.getAttackrange()));
        tv_armor.setText(getString(R.string.s_stat_format, stat.getArmor(), stat.getArmorperlevel()));
        tv_magic_resist.setText(getString(R.string.s_stat_format, stat.getSpellblock(), stat.getSpellblockperlevel()));
        tv_movement.setText(String.valueOf(stat.getMovespeed()));
    }
}
