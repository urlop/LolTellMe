package com.example.ruby.loltellme.ui.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.models.champion_model.DBChampion;
import com.example.ruby.loltellme.models.item_model.Item;
import com.example.ruby.loltellme.ui.adapter.SectionsPagerAdapter;
import com.example.ruby.loltellme.utils.Constants;
import com.example.ruby.loltellme.utils.custom_views.WrapContentHeightViewPager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionDetailActivity extends AppCompatActivity {

    @Bind(R.id.iv_champion)
    ImageView iv_champion;
    @Bind(R.id.iv_special)
    ImageView iv_special;
    @Bind(R.id.tv_title)
    TextView tv_title;

    Champion champion;
    Item item;
    WrapContentHeightViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);
        ButterKnife.bind(this);

        getExtra();
        setupView();
    }

    private void getExtra(){
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int id = extras.getInt(Constants.EXTRA_CHAMPION_ID);
            DBChampion dbChampion = DBChampion.find(DBChampion.class, "champion_id = ?", String.valueOf(id)).get(0);
            champion = dbChampion.parseChampion();
        }
    }

    private void setupView(){
        //TOOLBAR SETUP
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (WrapContentHeightViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        //ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(champion.getName());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    mViewPager.setPagingEnabled(false);
                } else {
                    mViewPager.setPagingEnabled(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //CONTENT SETUP
        Picasso.with(this).load(champion.getSplashImage()).placeholder(R.drawable.find).error(R.drawable.find).into(iv_champion);
        if (!champion.isActive()) {
            iv_special.setImageResource(R.drawable.ic_do_not_disturb_white_24dp);
        }else if (champion.isFreeToPlay()) {
            iv_special.setImageResource(R.drawable.ic_stars_white_24dp);
        }
        iv_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String specialDescription = "";
                if (!champion.isActive()) {
                    specialDescription = getString(R.string.special_description_disabled);
                }else if (champion.isFreeToPlay()) {
                    specialDescription = getString(R.string.special_description_free_to_play);
                }
                Toast.makeText(ChampionDetailActivity.this, specialDescription, Toast.LENGTH_SHORT).show();
            }
        });

        tv_title.setText(champion.getTitle());
    }

    public Champion getChampion() {
        return champion;
    }

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
