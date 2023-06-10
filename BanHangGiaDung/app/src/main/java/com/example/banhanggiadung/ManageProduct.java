package com.example.banhanggiadung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.banhanggiadung.adapter.ManagerProductAdapter;
import com.example.banhanggiadung.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ManageProduct extends AppCompatActivity {
ManagerProductAdapter managerProductAdapter;
    EditText name, price, image, description,category;
    Button btn_save_,btn_back_;
    Button btn_add;
    Button btn_back;
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);


        recyclerView = findViewById(R.id.list_products_manager);
        Button btn_add = findViewById(R.id.btn_add_);
        Button btn_back = findViewById(R.id.btn_back_);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        Anhxa();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(btn_add.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_add_product))
                        .setExpanded(true, 2400)
                        .create();

                dialogPlus.show();


//                btn_save_.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Map<String,Object> map = new HashMap<>();
//                        map.put("name", name.getText().toString());
//                        map.put("price", price.getText().toString());
//                        map.put("image", image.getText().toString());
//                        map.put("description", description.getText().toString());
//                        map.put("category_id", category.getText().toString());
//                        FirebaseDatabase.getInstance().getReference().child("products").push().setValue(map);
//                    }
//                });
//
//                btn_back_.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext() , ManageProduct.class );
//                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                        startActivity(intent);
//                    }
//                });
            }
        });

        FirebaseRecyclerOptions<Product> options =
               new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
                        .build();
       managerProductAdapter = new ManagerProductAdapter(options);

       LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getApplicationContext());
       layoutManagerCategory.setOrientation(LinearLayoutManager.VERTICAL);
       GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(), 1);
        recyclerView .setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(layoutManagerCategory);
       recyclerView.setAdapter(managerProductAdapter);

       onStart();
       onStop();
    }

        public void Anhxa() {
        EditText name = findViewById(R.id.name_update_add);
             price = findViewById(R.id.price_update_add);
             image = findViewById(R.id.image_update_add);
             description = findViewById(R.id.description_update_add);
             category = findViewById(R.id.category_update_add);
         btn_save_ = (Button) findViewById(R.id.btn_save_add);
         btn_back_ = (Button) findViewById(R.id.btn_back_add);
    }

    private void insertData()
        {
        Map<String,Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("price", price.getText().toString());
        map.put("image", image.getText().toString());
        map.put("description", description.getText().toString());
        map.put("category_id", category.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("products").push().setValue(map);
    }
    @Override
    public void onStart() {
        super.onStart();

        managerProductAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        managerProductAdapter.stopListening();
    }
}