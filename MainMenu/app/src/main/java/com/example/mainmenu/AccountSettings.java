package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountSettings extends AppCompatActivity {

    private EditText newUserNickName, newUserDOB, newUserEmail, newUserClass, newUserStudentNumber, newUserPhoneNumber, newUserName, newUserSurName;
    private Button UPUpdateButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private HashMap<String,Object> newItemsBurrowed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        newUserNickName = findViewById(R.id.UPUserNickName);
        newUserDOB = findViewById(R.id.UPUserDateBirth);
        newUserEmail = findViewById(R.id.UPUserEmail);
        newUserClass = findViewById(R.id.UPUserClass);
        newUserStudentNumber = findViewById(R.id.UPUserstdNr);
        newUserPhoneNumber = findViewById(R.id.UPUserPhone);
        newUserName = findViewById(R.id.UpUserName);
        newUserSurName = findViewById(R.id.UPUserSurName);
        UPUpdateButton = findViewById(R.id.UPUpdateButton);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                newUserNickName.setText(userprofile.getUserNickName());
                newUserDOB.setText(userprofile.getUserBday());
                newUserEmail.setText(userprofile.getUserEmail());
                newUserName.setText(userprofile.getUserName());
                newUserSurName.setText(userprofile.getUserSurname());
                newUserClass.setText(userprofile.getUserClass());
                newUserStudentNumber.setText(userprofile.getUserID());
                newUserPhoneNumber.setText(userprofile.getUserPhone());
                newItemsBurrowed = (userprofile.getItemsBorrowed());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AccountSettings.this, databaseError.getCode(), Toast.LENGTH_SHORT);

            }
        });
    UPUpdateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Nickname = newUserNickName.getText().toString();
            String DOB = newUserDOB.getText().toString();
            String email = newUserEmail.getText().toString();
            String Name = newUserName.getText().toString();
            String Surname = newUserSurName.getText().toString();
            String Class = newUserClass.getText().toString();
            String Studentnr = newUserStudentNumber.getText().toString();
            String Phone = newUserPhoneNumber.getText().toString();

            userProfile userprofile = new userProfile(Name, Surname, Nickname, DOB, email, Studentnr, Phone, Class, "User", newItemsBurrowed);

            databaseReference.setValue(userprofile);

            finish();
        }
    });

    }
}
