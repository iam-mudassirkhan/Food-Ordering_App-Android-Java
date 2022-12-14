package com.example.foodordringapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordringapp.DBHelper;
import com.example.foodordringapp.Models.OrderModel;
import com.example.foodordringapp.Ordered_Activity;
import com.example.foodordringapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.orderHolder> {

    ArrayList<OrderModel> orderList;
    Context orderContext;

    public OrderAdapter(ArrayList<OrderModel> orderList, Context orderContext) {
        this.orderList = orderList;
        this.orderContext = orderContext;
    }

    @NonNull
    @Override
    public orderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(orderContext).inflate(R.layout.orderd_sample, parent, false);
        return new orderHolder(view);

    }
    public void removeItem(int position) {
        orderList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull orderHolder holder, int position) {

        final OrderModel model = orderList.get(position);

        holder.orderImage.setImageResource(model.getOrderImage());
        holder.orderName.setText(model.getOrderName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.orderPrice.setText(model.getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(orderContext, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()) );
                intent.putExtra("type", 2);
                orderContext.startActivity(intent);
                notifyItemRemoved(position);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Dialog Box code
                new AlertDialog.Builder(orderContext)
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setTitle("Delete")
                        .setMessage("Are you Sure ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper helper = new DBHelper(orderContext);
                                if (helper.deleteOrder(model.getOrderNumber())> 0){
                                    Toast.makeText(orderContext, "Order Deleted from Database", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(orderContext, "Deleting Failed", Toast.LENGTH_SHORT).show();
                                }
                            }



                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();




                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class orderHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView orderName, orderNumber, orderPrice;

        public orderHolder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.orderImage);
            orderName = itemView.findViewById(R.id.orderfoodName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            orderPrice = itemView.findViewById(R.id.orderPrice);

        }
    }

}
