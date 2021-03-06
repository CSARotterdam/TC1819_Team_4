package com.example.mainmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileNickName, profileBday, profileEmail, profileName, profileSurname, profileClass, profileStudentNR, profilePhoneNumber;
    private Button profileUpdate, changePassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList <String> itemsBorrowed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileNickName = findViewById(R.id.tvProfileNickname);
        profileBday = findViewById(R.id. tvProfileBday);
        profileEmail = findViewById(R.id. tvProfileEmail);
        profileName = findViewById(R.id. tvProfileName);
        profileSurname = findViewById(R.id. tvProfileSurname);
        profileClass = findViewById(R.id. tvProfileClass);
        profileStudentNR = findViewById(R.id. tvProfileID);
        profilePhoneNumber = findViewById(R.id. tvProfilePhone);
        profileUpdate = findViewById(R.id.btnProfileUpdate);
        changePassword = findViewById(R.id.btnChangePassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                profileNickName.setText("NickName: " + userprofile.getUserNickName());
                profileBday.setText("Birthday: " + userprofile.getUserBday());
                profileEmail.setText("Email: " + userprofile.getUserEmail());
                profileName.setText("Name: " + userprofile.getUserName());
                profileSurname.setText("Surname: " + userprofile.getUserSurname());
                profileClass.setText("Class: " + userprofile.getUserClass());
                profileStudentNR.setText("StudentNr: " + userprofile.getUserID());
                profilePhoneNumber.setText("Phone: " + userprofile.getUserPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT);
                throw databaseError.toException();

            }
        });

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AccountSettings.class));
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UpdatePassword.class));
            }
        });
    }
}
