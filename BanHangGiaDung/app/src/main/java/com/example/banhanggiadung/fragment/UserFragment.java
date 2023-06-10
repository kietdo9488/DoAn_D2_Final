package com.example.banhanggiadung.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.banhanggiadung.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserFragment extends AbstractFragment  {
    //tao doi tuong
    private TextView showUsername, showEmail, showPhone,  showPassword;
    private TextView titleName;
    private Button editProfile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.user_layout, container, false);
        showUsername = fragmentLayout.findViewById(R.id.userName);
        showEmail = fragmentLayout.findViewById(R.id.email);
        showPhone = fragmentLayout.findViewById(R.id.phone);
        showPassword = fragmentLayout.findViewById(R.id.password);
        editProfile = fragmentLayout.findViewById(R.id.editButton);
        showUserData();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
        return fragmentLayout;
    }


    public void showUserData(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users");
//
//        String usernameUser = intent.getStringExtra("userName");
//        String emailUser = intent.getStringExtra("email");
//        String phonelUser = intent.getStringExtra("phone");
//        String passwordUser = intent.getStringExtra("password");
//
//        showUsername.setText(usernameUser);
//        showEmail.setText(emailUser);
//        showUsername.setText(phonelUser);
//        showPassword.setText(passwordUser);
    }

    public void passUserData() {
//        String userUsername = showUsername.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("userName").equalTo(userUsername);
//
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//
//                    String usernameFromDB = snapshot.child(userUsername).child("userName").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String phonelFromDB = snapshot.child(userUsername).child("phone").getValue(String.class);
//                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
//
//                    Intent intent = new Intent(UserFragment.this, EditUserActivity.class);
//
//                    intent.putExtra("userName", usernameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("phone", phonelFromDB);
//                    intent.putExtra("password", passwordFromDB);
//
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}