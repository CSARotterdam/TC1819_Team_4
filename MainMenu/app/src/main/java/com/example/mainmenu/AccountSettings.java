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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountSettings extends AppCompatActivity {

    private EditText newUserNickName, newUserDOB, newUserClass, newUserStudentNumber, newUserPhoneNumber, newUserName, newUserSurName;
    private Button UPUpdateButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private String newUserRole, newUserEmail;
    private HashMap<String, Object> newItemsBorrowed;
    private HashMap<String, Object> newItemsReserved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        newUserNickName = findViewById(R.id.UPUserNickName);
        newUserDOB = findViewById(R.id.UPUserDateBirth);
        newUserClass = findViewById(R.id.UPUserClass);
        newUserStudentNumber = findViewById(R.id.UPUserstdNr);
        newUserPhoneNumber = findViewById(R.id.UPUserPhone);
        newUserName = findViewById(R.id.UpUserName);
        newUserSurName = findViewById(R.id.UPUserSurName);
        UPUpdateButton = findViewById(R.id.UPUpdateButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                newUserNickName.setText(userprofile.getUserNickName());
                newUserDOB.setText(userprofile.getUserBday());
                newUserName.setText(userprofile.getUserName());
                newUserSurName.setText(userprofile.getUserSurname());
                newUserClass.setText(userprofile.getUserClass());
                newUserStudentNumber.setText(userprofile.getUserID());
                newUserPhoneNumber.setText(userprofile.getUserPhone());
                newUserEmail = (userprofile.getUserEmail());
                String role = userprofile.getUserRole();
                newItemsBorrowed = (userprofile.getItemsBorrowed());
                newItemsReserved = (userprofile.getItemsReserved());
                if (role.equals("Admin")){
                    newUserRole = "Admin";
                } else{
                    newUserRole = "User";
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AccountSettings.this, databaseError.getCode(), Toast.LENGTH_SHORT);

            }
        });
        UPUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile profile = new userProfile();

                String Nickname = newUserNickName.getText().toString();
                String DOB = newUserDOB.getText().toString();
                String Name = newUserName.getText().toString();
                String Surname = newUserSurName.getText().toString();
                String Class = newUserClass.getText().toString();
                String Studentnr = newUserStudentNumber.getText().toString();
                String Phone = newUserPhoneNumber.getText().toString();
                String User = newUserRole;
                String email = newUserEmail;

                userProfile userprofile = new userProfile(Name, Surname, Nickname, DOB, email, Studentnr, Phone, Class, User, newItemsReserved, newItemsBorrowed);

                databaseReference.setValue(userprofile);

                finish();
            }
        });

    }
}
