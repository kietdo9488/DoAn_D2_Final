package com.example.banhanggiadung.fragment;

import android.app.Activity;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.adapter.CategoryAdapter;
import com.example.banhanggiadung.adapter.CategoryAdapterKiet;
import com.example.banhanggiadung.adapter.HomeAdapter;
import com.example.banhanggiadung.adapter.ProductAdapter;
import com.example.banhanggiadung.model.Category;
import com.example.banhanggiadung.model.Category_Kiet;
import com.example.banhanggiadung.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends AbstractFragment {
    HomeAdapter homeAdapter;

    private CategoryAdapter categoryAdapter;

    CategoryAdapterKiet categoryAdapterKiet;

//    private DatabaseReference mDataBase;

    List<Product> arrProduct;

    List<Category> arrCategory;

    RecyclerView recyclerViewProductList;

    RecyclerView recyclerViewCategoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.product_list_layout, container, false);

         recyclerViewProductList = fragmentLayout.findViewById(R.id.list_product);
         recyclerViewCategoryList = fragmentLayout.findViewById(R.id.list_category);

        recyclerViewProductList.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));
        recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));


        ////
        //
        //Product List Adapter
//        arrProduct = new ArrayList<>();
//        arrProduct.add(new Product(1, "Tủ lạnh", 1000, R.mipmap.tu_lanh, "abc", 1));
//        arrProduct.add(new Product(2, "Máy giặt", 1000, R.mipmap.may_giat, "abc", 2));
//        arrProduct.add(new Product(3, "Cây lau nhà 1", 1000, R.mipmap.cay_lau_nha, "abc", 3));
//        arrProduct.add(new Product(4, "Cây lau nhà 2", 1000, R.mipmap.cay_lau_nha, "abc", 3));

//        arrCategory = new ArrayList<>();
//        arrCategory.add(0, new Category(1, "Tủ lạnh", R.mipmap.tu_lanh));
//        arrCategory.add(1, new Category(2, "Máy giặt", R.mipmap.may_giat));
//        arrCategory.add(2, new Category(3, "Gia dụng", R.mipmap.cay_lau_nha));


        //ket noi firebase product
        FirebaseRecyclerOptions<Product> optionsProduct =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
                        .build();
        homeAdapter = new HomeAdapter(optionsProduct);

        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(fragmentLayout.getContext(), 2);
        recyclerViewProductList.setLayoutManager(layoutManagerProduct);
        recyclerViewProductList.setLayoutManager(gridLayoutManagerProduct);
        recyclerViewProductList.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        getListProductsFromDatabase();
//        onStart();
//        onStop();


        //Ket noi firebase category

        FirebaseRecyclerOptions<Category_Kiet> optionsCategory =
                new FirebaseRecyclerOptions.Builder<Category_Kiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("categories"), Category_Kiet.class)
                        .build();
        categoryAdapterKiet = new CategoryAdapterKiet(optionsCategory);

        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(fragmentLayout.getContext(), 4);
        recyclerViewCategoryList.setLayoutManager(layoutManagerCategory);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategoryList.setAdapter(categoryAdapterKiet);
        getListCategoriesFromDatabase();
        onStart();
        onStop();


//        mDataBase = FirebaseDatabase.getInstance().getReference().child("categories");
//        recyclerViewCategoryList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDataBase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        arrProduct.clear();
//                        Product data = dataSnapshot.getValue(Product.class);
//                        arrProduct.add(data);
//                        productAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });

        //Create Layout manager for product
//        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(fragmentLayout.getContext());
//        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

//        recyclerViewProductList.setLayoutManager(layoutManagerProduct);
//        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(fragmentLayout.getContext(), 2);
//        recyclerViewProductList.setLayoutManager(gridLayoutManagerProduct);
//        productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
//        recyclerViewProductList.setAdapter(productAdapter);
//        productAdapter.notifyDataSetChanged();

        return fragmentLayout;

    }

    private void getListProductsFromDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("products");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String objectId = childSnapshot.getKey();
                    // Sử dụng objectId
                    Log.d("Firebase", "ID của đối tượng: " + objectId);
                }
//                Log.d("Du lieu", "onDataChange: " + product);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi: " + error.getMessage());
            }
        });
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
        homeAdapter.startListening();
        categoryAdapterKiet.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeAdapter.stopListening();
        categoryAdapterKiet.stopListening();
    }

    //    public static ArrayList<Product> getID(int id) {
//        Log.d("Tag", "" + id);
//        ArrayList<Product> productLists = new ArrayList<>();
//        for (int i = 0; i < arrProduct.size(); i++) {
//            if (arrProduct.get(i).IDSanPham == id) {
//                productLists.add(arrProduct.get(i));
//            }
//        }
//        return productLists;
//    }
}
