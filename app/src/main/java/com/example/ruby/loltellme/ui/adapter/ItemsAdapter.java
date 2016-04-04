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
import com.example.ruby.loltellme.models.item_model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ruby on 07/03/2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    List<Item> data = new ArrayList<>();
    private final LayoutInflater inflater;
    private final Context context;

    public ItemsAdapter(Context context, List<Item> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_grid_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item current = data.get(position);

        holder.tv_name.setText(current.getName());
        Picasso.with(context)
                    .load(current.getImage().getFull(Image.IMAGE_TYPE_ITEM))
                    .placeholder(R.drawable.find)
                    .error(R.drawable.find)
                    .into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Item removeItem(int position) {
        final Item model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Item model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Item model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<Item> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        //applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Item> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Item model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Item> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Item model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Item> newModels) {
        for (int toPosition = 0; toPosition <= newModels.size() - 1; toPosition++) {
            final Item model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_name;
        final ImageView iv_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}