package com.example.banhanggiadung;


import android.os.Bundle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;


public class AddProduct extends AppCompatActivity {
//    EditText name,price,image,description, category;
//    Button btn_add;
//    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertData();
//            }
//        });
//
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext() , ManageCategory.class );
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            }
//        });
    }

//    public void Anhxa() {
//        name = findViewById(R.id.name_update_add);
//         price = findViewById(R.id.price_update_add);
//         image = findViewById(R.id.image_update_add);
//         description = findViewById(R.id.description_update_add);
//         category = findViewById(R.id.category_update_add);
//        btn_add = (Button) findViewById(R.id.btn_save_add);
//        btn_back = (Button) findViewById(R.id.btn_back_add);
//    }
//    private void insertData()
//    {
//        Map<String,Object> map = new HashMap<>();
//        map.put("name", name.getText().toString());
//        map.put("price", price.getText().toString());
//        map.put("image", image.getText().toString());
//        map.put("description", description.getText().toString());
//        map.put("category_id", category.getText().toString());
//
//        FirebaseDatabase.getInstance().getReference().child("products").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(AddProduct.this, "Data insert successfull", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(AddProduct.this, "Error when insert data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
