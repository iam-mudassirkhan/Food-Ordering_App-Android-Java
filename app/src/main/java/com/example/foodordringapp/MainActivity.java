package com.example.foodordringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodordringapp.Adapters.MainAdapter;
import com.example.foodordringapp.Models.MainModel;
import com.example.foodordringapp.databinding.ActivityMainBinding;
import com.example.foodordringapp.databinding.FoodDisplayingSampleBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> myItemList = new ArrayList<>();

        myItemList.add(new MainModel(R.drawable.brownbread, "Chicken Breast", "5","Chicken breasts are as versatile as they are delicious and nutritious. Their mild flavour works as the perfect canvas for a variety of flavours"));

        myItemList.add(new MainModel(R.drawable.londonburger,"London Burger", "9","a popular sandwich made from ground meats that are formed into a patty, cooked, and placed between two halves of a bun"));

        myItemList.add(new MainModel(R.drawable.hamburger,"Ham Burger", "7","\n" +
                "This burger recipe uses minced or ground ham in place of beef (it is called a \"ham\" burger, isn't it?). Not to be confused with ground pork, minced ham is saltier but naturally leaner than most ground beef at less than five percent fat."));


        myItemList.add(new MainModel(R.drawable.chickenwings, "Chicken Wings", "3", "Buffalo chicken wings are fried chicken wings coated with a hot and tangy sauce. Buffalo chicken wings are traditionally fried without any breading.\n"));

        myItemList.add(new MainModel(R.drawable.smallpizza, "Small Pizza", "7", " Mini pizzas are just that, smaller, hand-held rounds of pizza, topped however you like. They are the perfect size for kids"));

        myItemList.add(new MainModel(R.drawable.midiumpizza, "Medium Pizza", "12", "Medium Pizza,. 4 pieces Cheesy Bread with two 345ml Pepsi. RS. 2399. 2 Medium Pizzas,. Side/Dessert of your choice with 1.5 liter Pepsi."));

        myItemList.add(new MainModel(R.drawable.largepizza, "Large Pizza", "20", "This Pizza has 12 Pieces with 1.5 litter Pepsi and it is 15% Off for this Weekend"));

        myItemList.add(new MainModel(R.drawable.shawerma, "Shawarma", "3", "Shawarma is thinly sliced cuts of meat, like chicken, beef, goat, lamb, and sometimes turkey rolled into a large piece of flatbread"));

        myItemList.add(new MainModel(R.drawable.specailpizza, "Special Pizza", "26", "It is our Restaurant Most Popular Pizza with delicious taste "));


        MainAdapter adapter = new MainAdapter(myItemList, this);
        binding.recyclerView.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.orders:
               startActivity(new Intent(MainActivity.this, Ordered_Activity.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}