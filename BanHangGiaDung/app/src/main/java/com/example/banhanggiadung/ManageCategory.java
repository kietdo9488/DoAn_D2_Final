package com.example.banhanggiadung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.banhanggiadung.adapter.CategoryAdapterKiet;
import com.example.banhanggiadung.model.Category_Kiet;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageCategory extends AppCompatActivity {

    CategoryAdapterKiet categoryAdapterKiet;
    RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        recyclerViewCategoryList = findViewById(R.id.list_category);

        //Ket noi firebase category

        FirebaseRecyclerOptions<Category_Kiet> optionsCategory =
                new FirebaseRecyclerOptions.Builder<Category_Kiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("categories"), Category_Kiet.class)
                        .build();
        categoryAdapterKiet = new CategoryAdapterKiet(optionsCategory);

        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getApplicationContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(getApplicationContext(), 4);
        recyclerViewCategoryList.setLayoutManager(layoutManagerCategory);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategoryList.setAdapter(categoryAdapterKiet);
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
        categoryAdapterKiet.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoryAdapterKiet.stopListening();
    }
}