package com.example.banhanggiadung.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.banhanggiadung.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EditUserActivity extends AppCompatActivity {
    //tao doi tuong
    EditText editUsername, editName, editEmail, editPhone, editPassword;
    Button saveButton;
    String usernameUser, nameUser, emailUser, userPhone,  passwordUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user2);
        //goi filebase de lay doi tuong user
        reference = FirebaseDatabase.getInstance().getReference("users");

        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);
        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isUserNameChanged() || isEmailChanged() || isPhonelChanged() || isPasswordChanged()) {
                    Toast.makeText(EditUserActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditUserActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //ham hay doi ten nguoi dung
    public boolean isUserNameChanged(){
        if (!nameUser.equals(editName.getText().toString())){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUserDatabase = reference.orderByChild("userName").equalTo(nameUser);
            reference.child(usernameUser).child("userName").setValue(editUsername.getText().toString());
            reference.child(usernameUser).child(checkUserDatabase.toString()).setValue(editUsername.getText().toString());
            usernameUser = editUsername.getText().toString();
            return true;
        } else{
            return false;
        }
    }
    //ham hay doi email nguoi dung
    public boolean isEmailChanged(){
        if (!emailUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else{
            return false;
        }
    }
    //ham hay doi so dien thoai nguoi dung
    public boolean isPhonelChanged(){
        if (!emailUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("phone").setValue(editPhone.getText().toString());
            userPhone = editPhone.getText().toString();
            return true;
        } else{
            return false;
        }
    }
    //ham hay doi mat khau nguoi dung
    public boolean isPasswordChanged(){
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();

        usernameUser = intent.getStringExtra("userName");
        emailUser = intent.getStringExtra("email");
        userPhone = intent.getStringExtra("phone");
        passwordUser = intent.getStringExtra("password");

        editUsername.setText(usernameUser);
        editEmail.setText(emailUser);
        editPhone.setText(userPhone);
        editPassword.setText(passwordUser);
    }
}