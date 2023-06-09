package com.example.banhanggiadung.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.fragment.ProductListFragment;
import com.example.banhanggiadung.model.Category;
import com.example.banhanggiadung.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Activity activity;
    private int layoutID;
    private ArrayList<Category> categoryList;

    public CategoryAdapter(Activity activity, int layoutID, ArrayList<Category> categoryList) {
        this.activity = activity;
        this.layoutID = layoutID;
        this.categoryList = categoryList;
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
        Category category = categoryList.get(position);
        int img = categoryList.get(position).getCategory_image();
        String name = String.valueOf(categoryList.get(position).getCategory_name());
//        holder.imgProduct.setImageResource(img);
        holder.imgCategory.setImageDrawable(activity.getResources().getDrawable(img, activity.getTheme()));
        holder.name_category.setText(name);

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProductListFragment.getID(category.getId());
            };
        };
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    protected static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgCategory;
        TextView name_category;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            name_category = itemView.findViewById(R.id.tv_name_category);

            imgCategory.setOnClickListener(this);
            name_category.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);

            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;

    }

    


//-------------Old Adapter---------------//
//    ArrayList<Category> arrCategory;
//    Context context;
//
//    @Override
//    public int getCount() {
//        return arrCategory.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return arrCategory.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    public class ViewHolder
//    {
//        TextView txtLoaiSanPham;
//        ImageView imgLoaiSanPham;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (view == null)
//        {
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
//            viewHolder.txtLoaiSanPham = (TextView) view.findViewById(R.id.textViewLoaiSp);
//            viewHolder.imgLoaiSanPham = (ImageView) view.findViewById(R.id.imageViewLoaiSp);
//            view.setTag(viewHolder);
//        }
//        else
//        {
//            viewHolder = (ViewHolder) view.getTag();
//
//        }
//        Category categoryModel = (Category) getItem(i);
//        viewHolder.txtLoaiSanPham.setText(categoryModel.getCategory_name());
//        Picasso.with(context).load(categoryModel.getCategory_image())
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .into(viewHolder.imgLoaiSanPham);
//        return view;
//    }
//
//    public CategoryAdapter(ArrayList<Category> arrCategory, Context context) {
//        this.arrCategory = arrCategory;
//        this.context = context;
//    }
}
