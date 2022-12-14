package com.example.foodordringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Adapter;

import com.example.foodordringapp.Adapters.OrderAdapter;
import com.example.foodordringapp.Models.MainModel;
import com.example.foodordringapp.Models.OrderModel;
import com.example.foodordringapp.databinding.ActivityOrderedBinding;

import java.util.ArrayList;

public class Ordered_Activity extends AppCompatActivity {

    ActivityOrderedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<OrderModel> myOrderList = helper.getOrders();


        OrderAdapter adapter = new OrderAdapter(myOrderList, this);
        binding.orderRecyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

//        myOrderList.add(new OrderModel(R.drawable.midiumpizza, "Medium Pizza", "120034", "12"));
//        myOrderList.add(new OrderModel(R.drawable.chickenwings, "Chicken Wings", "120254", "4"));
//        myOrderList.add(new OrderModel(R.drawable.midiumpizza, "Medium Pizza", "120034", "12"));
//        myOrderList.add(new OrderModel(R.drawable.chickenwings, "Chicken Wings", "120254", "4"));
//        myOrderList.add(new OrderModel(R.drawable.midiumpizza, "Medium Pizza", "120034", "12"));
//        myOrderList.add(new OrderModel(R.drawable.chickenwings, "Chicken Wings", "120254", "4"));
//        myOrderList.add(new OrderModel(R.drawable.midiumpizza, "Medium Pizza", "120034", "12"));
//        myOrderList.add(new OrderModel(R.drawable.chickenwings, "Chicken Wings", "120254", "4"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}