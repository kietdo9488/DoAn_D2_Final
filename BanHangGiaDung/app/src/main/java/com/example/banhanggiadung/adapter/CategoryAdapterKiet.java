package com.example.banhanggiadung.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.banhanggiadung.R;
import com.example.banhanggiadung.model.Category_Kiet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.nio.InvalidMarkException;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapterKiet extends FirebaseRecyclerAdapter<Category_Kiet,CategoryAdapterKiet.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryAdapterKiet(@NonNull FirebaseRecyclerOptions<Category_Kiet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Category_Kiet model) {
        holder.category_name.setText(model.getCategory_name());

        Glide.with(holder.category_img.getContext()).load(model.getCategory_img()).placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.category_img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_kiet, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView category_img;
        TextView category_name;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            category_img = (ImageView) itemView.findViewById(R.id.category_img);
            category_name = (TextView) itemView.findViewById(R.id.category_name);
        }
    }
}
