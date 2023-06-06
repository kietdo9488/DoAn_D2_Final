package com.example.banhanggiadung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhanggiadung.R;
import com.example.banhanggiadung.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<Category> arrCategory;
    Context context;

    @Override
    public int getCount() {
        return arrCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return arrCategory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder
    {
        TextView txtLoaiSanPham;
        ImageView imgLoaiSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txtLoaiSanPham = (TextView) view.findViewById(R.id.textViewLoaiSp);
            viewHolder.imgLoaiSanPham = (ImageView) view.findViewById(R.id.imageViewLoaiSp);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();

        }
        Category categoryModel = (Category) getItem(i);
        viewHolder.txtLoaiSanPham.setText(categoryModel.getCategory_name());
        Picasso.with(context).load(categoryModel.getCategory_image())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.imgLoaiSanPham);
        return view;
    }

    public CategoryAdapter(ArrayList<Category> arrCategory, Context context) {
        this.arrCategory = arrCategory;
        this.context = context;
    }
}
