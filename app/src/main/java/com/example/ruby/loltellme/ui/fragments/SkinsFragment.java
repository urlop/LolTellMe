package com.example.ruby.loltellme.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.example.ruby.loltellme.ui.adapter.SkillsPagerAdapter;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkinsFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class SkinsFragment extends Fragment {

    @Bind(R.id.pager)
    ViewPager pager;

    private static SkinsFragment instance;
    private ChampionDetailActivity activity;

    public static SkinsFragment getInstance(){
        if (instance == null){
            instance = new SkinsFragment();
            Bundle args = new Bundle();
            instance.setArguments(args);
        }
        return instance;
    }

    public SkinsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_skins, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    private void setupView(){
        pager.setAdapter(new SkillsPagerAdapter(activity));
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SkillPagerAdapter extends FragmentStatePagerAdapter {

        Context context;

        public SkillPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return GeneralFragment.newInstance();
                case 1:
                    return GeneralFragment.newInstance();
                case 2:
                    return GeneralFragment.newInstance();
                case 3:
                    return GeneralFragment.newInstance();
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
}
