package com.example.ruby.loltellme.ui.adapter;

/**
 * Created by Ruby on 13/03/2016.
 */

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.champion_model.Skin;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SkillsPagerAdapter extends PagerAdapter {

    ChampionDetailActivity activity;
    LayoutInflater mLayoutInflater;
    List<Skin> skins;

    public SkillsPagerAdapter(ChampionDetailActivity activity) {
        this.activity = activity;
        mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        skins = activity.getChampion().getSkins();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_pager_skin, container, false);

        ImageView iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        TextView tv_name = (TextView) itemView.findViewById(R.id.tv_name);

        String img = activity.getChampion().getSkinUrl(position);
        Picasso.with(activity).load(img).placeholder(R.drawable.find).error(R.drawable.find).into(iv_image);

        String name = activity.getChampion().getSkinName(position);
        tv_name.setText(name);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return skins.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}