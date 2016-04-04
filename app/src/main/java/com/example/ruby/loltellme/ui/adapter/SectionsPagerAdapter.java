package com.example.ruby.loltellme.ui.adapter;

/**
 * Created by Ruby on 13/03/2016.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.ui.fragments.GeneralFragment;
import com.example.ruby.loltellme.ui.fragments.SkinsFragment;
import com.example.ruby.loltellme.ui.fragments.StatsFragment;
import com.example.ruby.loltellme.ui.fragments.TipsFragment;

import java.util.Locale;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return GeneralFragment.newInstance();
            case 1:
                return StatsFragment.getInstance();
            case 2:
                return TipsFragment.getInstance();
            case 3:
                return SkinsFragment.getInstance();
            default:
                return GeneralFragment.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return context.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return context.getString(R.string.title_section3).toUpperCase(l);
            case 3:
                return context.getString(R.string.title_section4).toUpperCase(l);
        }
        return null;
    }
}