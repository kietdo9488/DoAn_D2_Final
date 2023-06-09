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

import com.example.banhanggiadung.adapter.CategoryAdapterKiet;
import com.example.banhanggiadung.adapter.CategoryAdapterManage;
import com.example.banhanggiadung.model.Category_Kiet;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageCategory extends AppCompatActivity {

    CategoryAdapterManage categoryAdapterManage;
    RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        recyclerViewCategoryList = findViewById(R.id.list_category);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , AddCategory.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        //Ket noi firebase category

        FirebaseRecyclerOptions<Category_Kiet> optionsCategory =
                new FirebaseRecyclerOptions.Builder<Category_Kiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("categories"), Category_Kiet.class)
                        .build();
        categoryAdapterManage = new CategoryAdapterManage(optionsCategory);

        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getApplicationContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(getApplicationContext(), 1);
        recyclerViewCategoryList.setLayoutManager(layoutManagerCategory);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategoryList.setAdapter(categoryAdapterManage);

        getListCategoriesFromDatabase();
        onStart();
        onStop();
    }

    private void getListCategoriesFromDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String objectId = childSnapshot.getKey();
                    // Sử dụng objectId
                    Log.d("Firebase", "ID của đối tượng: " + objectId);
                }
//                Log.d("Du lieu", "onDataChange: " + category);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi: " + error.getMessage());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        categoryAdapterManage.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoryAdapterManage.stopListening();
    }
}