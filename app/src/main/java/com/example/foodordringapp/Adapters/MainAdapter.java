package com.example.foodordringapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordringapp.Models.MainModel;
import com.example.foodordringapp.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.itemHolder> {

    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_displaying_sample, parent, false);

        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
        final MainModel model = list.get(position);
        holder.foodImage.setImageResource(model.getImage());
        holder.foodName.setText(model.getName());
        holder.foodPrice.setText(model.getPrice());
        holder.foodDescription.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("name", model.getName());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("type", 1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodName, foodPrice, foodDescription;

        public itemHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.orderImage);
            foodName = itemView.findViewById(R.id.orderfoodName);
            foodPrice = itemView.findViewById(R.id.orderPrice);
            foodDescription = itemView.findViewById(R.id.foodDescription);
        }
    }

}
