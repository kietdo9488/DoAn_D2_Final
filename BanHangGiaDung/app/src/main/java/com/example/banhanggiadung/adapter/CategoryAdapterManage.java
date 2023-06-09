package com.example.banhanggiadung.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
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
import com.example.banhanggiadung.model.Category_Kiet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class CategoryAdapterManage extends FirebaseRecyclerAdapter<Category_Kiet,CategoryAdapterManage.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryAdapterManage(@NonNull FirebaseRecyclerOptions<Category_Kiet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull Category_Kiet model) {
        holder.category_name.setText(model.getCategory_name());

        Glide.with(holder.category_img.getContext()).load(model.getCategory_img()).placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.category_img);

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.category_img.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup_category)).setExpanded(true, 1200).create();

//                Log.d("Log", "Call thanh conng");

                dialogPlus.show();
                View view = dialogPlus.getHolderView();
                EditText category_name = view.findViewById(R.id.txt_category_name);
                EditText category_img = view.findViewById(R.id.txt_category_img);
                Button btnUpdate = view.findViewById(R.id.btn_update);

                category_name.setText(model.getCategory_name());
                category_img.setText(model.getCategory_img());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("category_name", category_name.getText().toString());
                        map.put("category_img", category_img.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("categories").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.category_name.getContext(), "Data Update Successfull", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( Exception e) {
                                Toast.makeText(holder.category_name.getContext(), "Error, Data Update Failed", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.category_name.getContext());
                builder.setTitle("Delete?");
                builder.setMessage("Ban chac chan muon xoa?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("categories").child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.category_name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_category, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView category_img;
        TextView category_name;

        Button btn_edit;
        Button btn_delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            category_img = (ImageView) itemView.findViewById(R.id.category_img);
            category_name = (TextView) itemView.findViewById(R.id.category_name);

            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);

        }
    }
}
