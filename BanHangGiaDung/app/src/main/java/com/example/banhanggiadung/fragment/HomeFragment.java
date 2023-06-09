package com.example.banhanggiadung.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.adapter.HomeAdapter;
import com.example.banhanggiadung.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AbstractFragment {



    private RecyclerView recyclerView;
    ViewFlipper viewFlipper;

    List<Product> listProducts;
    HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.home_layout, container, false);
        viewFlipper = fragmentLayout.findViewById(R.id.viewFlipper);
        ActionViewFlipper();
        recyclerView = fragmentLayout.findViewById(R.id.list_product_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));

                FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
                        .build();
        homeAdapter = new HomeAdapter(options);

        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentLayout.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        getListProductsFromDatabase();
        onStart();
        onStop();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentLayout.getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentLayout.getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentLayout.getContext(), 2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        listProducts = new ArrayList<>();
//        pdAdapter = new PdAdapter(listProducts);

//        recyclerView.setAdapter(pdAdapter);

//        getListProductsFromDatabase();
        //Product List Adapter
//        ArrayList<Product> arrProduct = new ArrayList<>();
////        arrProduct.add(new Product(1, "Tu lanh", 1000, R.mipmap.tu_lanh, "abc", 1));
////        arrProduct.add(new Product(2, "May giat", 1000, R.mipmap.may_giat, "abc", 2));
////        arrProduct.add(new Product(3, "Cay lau nha 1", 1000, R.mipmap.cay_lau_nha, "abc", 3));
////        arrProduct.add(new Product(4, "Cay lau nha 2", 1000, R.mipmap.cay_lau_nha, "abc", 4));

//        RecyclerView recyclerView = fragmentLayout.findViewById(R.id.list_product_home);
//        ProductAdapter productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));
        // Kết nối với Firebase




        //Create Layout manager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentLayout.getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerView.setLayoutManager(layoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentLayout.getContext(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
//        recyclerView.setAdapter(productAdapter);
//        productAdapter.notifyDataSetChanged();

//        recyclerView = fragmentLayout.findViewById(R.id.list_product_home);
//        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentLayout.getContext()));

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("products");
//        FirebaseRecyclerOptions<Product> options =
//                new FirebaseRecyclerOptions.Builder<Product>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("employee"), Product.class)
//                        .build();
//
//        madapter = new adapter(options);
//        recyclerView.setAdapter(madapter);


        return fragmentLayout;
    }


    @Override
    public void onStart() {
        super.onStart();
        homeAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeAdapter.startListening();
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

    private void ActionViewFlipper()
    {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://cdn.tgdd.vn/2021/11/CookDish/cac-loai-do-gia-dung-hien-dai-cho-can-bep-nha-ban-them-tien-avt-1200x676.jpg");
        mangQuangCao.add("https://giadungsato.com/Uploads/images/giadungsato(2).jpg");
        mangQuangCao.add("https://ominsu.com.vn/wp-content/uploads/2017/09/Anh-NOI-CHAO-.jpg");
        for (int i = 0; i< mangQuangCao.size(); i++)
        {
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }

}