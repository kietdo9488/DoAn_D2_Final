package com.example.banhanggiadung.adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.model.Product;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Activity activity;
    private int layoutID;
    private ArrayList<Product> productList;

    public ProductAdapter(Activity activity, int layoutID, ArrayList<Product> productList) {
        this.activity = activity;
        this.layoutID = layoutID;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int img = productList.get(position).getHinhSanPham();
        String name = String.valueOf(productList.get(position).getTenSanPham());
        String price = String.valueOf(productList.get(position).getGiaSanPham());
//        holder.imgProduct.setImageResource(img);
        holder.imgProduct.setImageDrawable(activity.getResources().getDrawable(img, activity.getTheme()));
        holder.name_product.setText(name);
        holder.price_product.setText(price);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView name_product;
        TextView price_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            name_product = itemView.findViewById(R.id.tv_name_product);
            price_product = itemView.findViewById(R.id.tv_price_product);


        }
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;

    }
}
