package com.example.banhanggiadung.fragment;

import android.app.Activity;
import android.os.Bundle;
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
import com.example.banhanggiadung.adapter.ProductAdapter;
import com.example.banhanggiadung.model.Product;

import java.util.ArrayList;

public class ProductListFragment extends AbstractFragment {
    private ProductAdapter productAdapter;

    private CategoryAdapter categoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.product_list_layout, container, false);



        ////
        //
        //Product List Adapter
        ArrayList<Product> arrProduct = new ArrayList<>();
        arrProduct.add(new Product(1, "San pham 1", 1000, R.mipmap.ic_launcher_round, "abc", 1));
        arrProduct.add(new Product(2, "San pham 2", 1000, R.mipmap.ic_launcher_round, "abc", 2));
        arrProduct.add(new Product(3, "San pham 3", 1000, R.mipmap.ic_launcher_round, "abc", 3));
        arrProduct.add(new Product(4, "San pham 4", 1000, R.mipmap.ic_launcher_round, "abc", 4));

        RecyclerView recyclerView = fragmentLayout.findViewById(R.id.list_product);


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
}
