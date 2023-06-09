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
    private ProductAdapter productAdapter;

    private CategoryAdapter categoryAdapter;

    private CategoryAdapterKiet categoryAdapterKiet;

//    private DatabaseReference mDataBase;

    ArrayList<Product> arrProduct;

    ArrayList<Category> arrCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.product_list_layout, container, false);


        ////
        //
        //Product List Adapter
        arrProduct = new ArrayList<>();
        arrProduct.add(new Product(1, "Tủ lạnh", 1000, R.mipmap.tu_lanh, "abc", 1));
        arrProduct.add(new Product(2, "Máy giặt", 1000, R.mipmap.may_giat, "abc", 2));
        arrProduct.add(new Product(3, "Cây lau nhà 1", 1000, R.mipmap.cay_lau_nha, "abc", 3));
        arrProduct.add(new Product(4, "Cây lau nhà 2", 1000, R.mipmap.cay_lau_nha, "abc", 3));

        arrCategory = new ArrayList<>();
        arrCategory.add(0, new Category(1, "Tủ lạnh", R.mipmap.tu_lanh));
        arrCategory.add(1, new Category(2, "Máy giặt", R.mipmap.may_giat));
        arrCategory.add(2, new Category(3, "Gia dụng", R.mipmap.cay_lau_nha));


        RecyclerView recyclerViewProductList = fragmentLayout.findViewById(R.id.list_product);
        RecyclerView recyclerViewCategoryList = fragmentLayout.findViewById(R.id.list_category);

        //Create Layout manager for category
//        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(fragmentLayout.getContext());
//        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewCategoryList.setLayoutManager(layoutManagerCategory);
//        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(fragmentLayout.getContext(), 5);
//        recyclerViewCategoryList.setLayoutManager(gridLayoutManagerCategory);
//        categoryAdapter = new CategoryAdapter((Activity) fragmentLayout.getContext(), R.layout.item_categories, arrCategory);
//        recyclerViewCategoryList.setAdapter(categoryAdapter);
//        categoryAdapter.notifyDataSetChanged();

        //Ket noi firebase

        FirebaseRecyclerOptions<Category_Kiet> options = new FirebaseRecyclerOptions.Builder<Category_Kiet>().setQuery(FirebaseDatabase.getInstance().getReference().child("categories"), Category_Kiet.class).build();
        categoryAdapterKiet = new CategoryAdapterKiet(options);
        recyclerViewCategoryList.setAdapter(categoryAdapter);

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
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManagerProduct.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewProductList.setLayoutManager(layoutManagerProduct);
        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(fragmentLayout.getContext(), 2);
        recyclerViewProductList.setLayoutManager(gridLayoutManagerProduct);
        productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
        recyclerViewProductList.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        return fragmentLayout;

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
