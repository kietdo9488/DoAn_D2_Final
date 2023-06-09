package com.example.banhanggiadung.adapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.banhanggiadung.R;
import com.example.banhanggiadung.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    //    private Activity activity;
//    private int layoutID;
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
//        this.activity = activity;
//        this.layoutID = layoutID;
        this.productList = productList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_products, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product data = productList.get(position);
//        holder.imgProduct.setImageDrawable(data.getHinhSanPham());
//        int img = productList.get(position).getHinhSanPham();
        Glide.with(holder.itemView.getContext())
                .asBitmap()
//                .load(img)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        // Chuyển đổi Bitmap thành kiểu int
                        int imageInt = resource.getPixel(0, 0);
                        holder.imgProduct.setImageResource((int) imageInt);
                    }
                });
//        holder.name_product.setText(data.getTenSanPham());
//        holder.price_product.setText(data.getGiaSanPham());
//        String name = String.valueOf(productList.get(position).getTenSanPham());
//        String price = String.valueOf(productList.get(position).getGiaSanPham());
////        holder.imgProduct.setImageResource(img);
//        holder.imgProduct.setImageDrawable(activity.getResources().getDrawable(img, activity.getTheme()));
//        holder.name_product.setText(name);
//        holder.price_product.setText(price);
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
            name_product = itemView.findViewById(R.id.name_product);
            price_product = itemView.findViewById(R.id.price_product);


        }


    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
//    @Override
//    public int getItemViewType(int position) {
//        return layoutID;
//
//    }
}
