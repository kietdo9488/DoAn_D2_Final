package com.example.banhanggiadung.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.banhanggiadung.R;
import com.example.banhanggiadung.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ManagerProductAdapter extends FirebaseRecyclerAdapter<Product, ManagerProductAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ManagerProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ManagerProductAdapter.myViewHolder holder, int position, @NonNull Product model) {
        holder.name.setText(model.getName());
//        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_product_layout))
                        .setExpanded(true, 2300)
                        .create();
                dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.name_update);
                EditText price = view.findViewById(R.id.price_update);
                EditText image = view.findViewById(R.id.image_update);
                EditText description = view.findViewById(R.id.description_update);
                EditText category = view.findViewById(R.id.category_update);
                Button btnUpdate = view.findViewById(R.id.btn_update_);


                name.setText(model.getName());
                image.setText(model.getImage());
                description.setText(model.getDescription());

                //Ep kieu cho gia tri kieu du lieu int
                int valueP = model.getPrice();
                String valuePrice = String.valueOf(valueP);
                price.setText(valuePrice);
                int valueC = model.getCategory_id();
                String valueCate = String.valueOf(valueC);
                category.setText(valueCate);
                //
                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        String vp = price.getText().toString();
                        int p = Integer.parseInt(vp);
                        map.put("price", price.getText().toString());
                        map.put("image", image.getText().toString());
                        map.put("description", description.getText().toString());
                        map.put("category_id", category.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("products").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Data Update Fail", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
                                }
                        });
                    }
                });
            }
        });

        //Delete
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Delete?");
                builder.setMessage("Ban chac chan muon xoa?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("products").child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public ManagerProductAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products_manager_layout, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;
        Button btn_update, btn_del;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_crud);
            name = (TextView) itemView.findViewById(R.id.txt_product_name_crud);
            price = (TextView) itemView.findViewById(R.id.txt_product_cate_crud);
            btn_update = (Button) itemView.findViewById(R.id.bt_product_edit_crud);
            btn_del = (Button) itemView.findViewById(R.id.bt_product_delete_crud);
        }
    }
}
