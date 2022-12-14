      package com.example.foodordringapp.Adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodordringapp.DBHelper;
import com.example.foodordringapp.Ordered_Activity;
import com.example.foodordringapp.R;
import com.example.foodordringapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

//    by default Minimum Quantity
     int minQuantity = 1;

    ActivityDetailBinding binding;
    public TextWatcher  textWatcher ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Calling database Class Here
        final DBHelper helper = new DBHelper(this);


        // Code For Disabling Order Button when PhoneBox and NameBox is empty
        binding.orderbtn.setEnabled(false);


        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String n = binding.nameBox.getText().toString();
                String phoneNum = binding.phoneBox.getText().toString();
                if (!n.isEmpty() & !phoneNum.isEmpty()) {
                    binding.orderbtn.setEnabled(true);
                } else {
                    binding.orderbtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        binding.phoneBox.addTextChangedListener(textWatcher);
        binding.nameBox.addTextChangedListener(textWatcher);

        if (getIntent().getIntExtra("type",0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");

            binding.detailImage.setImageResource(image);
            binding.detailOrderPrice.setText(String.format("%d", price));
            binding.detailFoodName.setText(name);
            binding.detailFoodDescription.setText(description);


//             Button Code
            binding.orderbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString())


                    );


                    //        Displaying Error message to User When PhoneBox and NameBox is Empty
//                    if (TextUtils.isEmpty(binding.nameBox.getText().toString()) ) {
//                        binding.nameBox.setError("Can't be Empty");
//
//                    }
//                    else if(TextUtils.isEmpty(binding.phoneBox.getText().toString())){
//                        binding.phoneBox.setError("Can't be Empty");
//                    }
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, name + " Order Placed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailActivity.this, Ordered_Activity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {

            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);

//        we Use 1 2, 3, 4, etc... these numbers are Columns index number in DBHelper Class...

            final int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.detailOrderPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.detailFoodName.setText(cursor.getString(6));
            binding.detailFoodDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.quantity.setText(cursor.getString(7));

            binding.orderbtn.setText("Update Now");
            binding.orderbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               boolean  isUpdated =  helper.updateOrder(
                         binding.nameBox.getText().toString(),
                         binding.phoneBox.getText().toString(),
                         Integer.parseInt(binding.detailOrderPrice.getText().toString()),
                         image,
                       binding.detailFoodDescription.getText().toString(),
                         binding.detailFoodName.getText().toString(),
                          Integer.parseInt(binding.quantity.getText().toString()),
                         id

                         );
               if (isUpdated){
                   Toast.makeText(DetailActivity.this, " Order Updated", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(DetailActivity.this, Ordered_Activity.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
                }
            });

        }

        }
        public void onIncrement (View view){


            display(minQuantity);

              if (minQuantity==12){
                Toast.makeText(this, "Contact to our Restaurant for further Orders", Toast.LENGTH_LONG).show();
            } else if (minQuantity >=1) {
                  minQuantity = minQuantity + 1;
              }

        }

        public void onDecrement (View view){
            display(minQuantity);


            if (minQuantity >1){
                minQuantity = minQuantity - 1;

            }

            else if (minQuantity==1){
                Toast.makeText(this, "Can't be Decrement", Toast.LENGTH_SHORT).show();
            }

        }

    private void display(int number) {
        binding.quantity.setText("" + number);
    }
    }