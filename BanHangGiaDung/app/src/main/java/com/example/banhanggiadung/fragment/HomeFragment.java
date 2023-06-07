package com.example.banhanggiadung.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.adapter.ProductAdapter;
import com.example.banhanggiadung.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends AbstractFragment {

    private ProductAdapter productAdapter;

    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.home_layout, container, false);
        viewFlipper = fragmentLayout.findViewById(R.id.viewFlipper);


        //
        ActionViewFlipper();


        //Product List Adapter
        ArrayList<Product> arrProduct = new ArrayList<>();
        arrProduct.add(new Product(1, "San pham 1", 1000, R.mipmap.ic_launcher_round, "abc", 1));
        arrProduct.add(new Product(2, "San pham 2", 1000, R.mipmap.ic_launcher_round, "abc", 2));
        arrProduct.add(new Product(3, "San pham 3", 1000, R.mipmap.ic_launcher_round, "abc", 3));
        arrProduct.add(new Product(4, "San pham 4", 1000, R.mipmap.ic_launcher_round, "abc", 4));

        RecyclerView recyclerView = fragmentLayout.findViewById(R.id.list_product_home);


        //Create Layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentLayout.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentLayout.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter((Activity) fragmentLayout.getContext(), R.layout.item_products, arrProduct);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();


        return fragmentLayout;
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