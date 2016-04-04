package com.example.ruby.loltellme.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruby.loltellme.R;
import com.example.ruby.loltellme.models.Image;
import com.example.ruby.loltellme.models.champion_model.Spell;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ruby on 07/03/2016.
 */

public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.MyViewHolder> {
    List<Spell> data = new ArrayList<>();
    private final LayoutInflater inflater;
    private final Context context;

    public SpellsAdapter(Context context, List<Spell> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grid_spell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Spell current = data.get(position);

        holder.tv_name.setText(current.getName());
        Picasso.with(context)
                    .load(current.getImage().getFull(Image.IMAGE_TYPE_SPELL))
                    .placeholder(R.drawable.find)
                    .error(R.drawable.find)
                    .into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Spell removeSpell(int position) {
        final Spell model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addSpell(int position, Spell model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveSpell(int fromPosition, int toPosition) {
        final Spell model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<Spell> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        //applyAndAnimateMovedSpells(models);
    }

    private void applyAndAnimateRemovals(List<Spell> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Spell model = data.get(i);
            if (!newModels.contains(model)) {
                removeSpell(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Spell> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Spell model = newModels.get(i);
            if (!data.contains(model)) {
                addSpell(i, model);
            }
        }
    }

    private void applyAndAnimateMovedSpells(List<Spell> newModels) {
        for (int toPosition = 0; toPosition <= newModels.size() - 1; toPosition++) {
            final Spell model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveSpell(fromPosition, toPosition);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_name;
        final ImageView iv_image;

        public MyViewHolder(View spellView) {
            super(spellView);
            tv_name = (TextView) spellView.findViewById(R.id.tv_name);
            iv_image = (ImageView) spellView.findViewById(R.id.iv_image);
        }
    }
}