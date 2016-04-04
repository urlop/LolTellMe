package com.example.ruby.loltellme.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.champion_model.Champion;
import com.example.ruby.loltellme.ui.activities.ChampionDetailActivity;
import com.example.ruby.loltellme.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ruby on 07/03/2016.
 */

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.MyViewHolder> {
    List<Champion> data = new ArrayList<>();
    private final LayoutInflater inflater;
    private final Context context;

    public ChampionsAdapter(Context context, List<Champion> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grid_champion, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Champion current = data.get(position);

        holder.tv_name.setText(current.getName());
        Picasso.with(context)
                    .load(current.getImage().getFull(Image.IMAGE_TYPE_CHAMPION))
                    .placeholder(R.drawable.find)
                    .error(R.drawable.find)
                    .into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Champion removeItem(int position) {
        final Champion model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Champion model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Champion model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<Champion> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        //applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Champion> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Champion model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Champion> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Champion model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Champion> newModels) {
        for (int toPosition = 0; toPosition <= newModels.size() - 1; toPosition++) {
            final Champion model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView tv_name;
        final ImageView iv_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }

        @Override
        public void onClick(View v) {
            Champion element = data.get(getAdapterPosition());
            Intent intent = new Intent(v.getContext(), ChampionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_CHAMPION_ID, element.getId());
            v.getContext().startActivity(intent);
        }
    }
}