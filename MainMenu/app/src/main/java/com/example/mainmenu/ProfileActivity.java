package com.example.mainmenu;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileNickName, profileBday, profileEmail, profileName, profileSurname, profileClass, profileStudentNR, profilePhoneNumber;
    private Button profileUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private String Role;

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

        Role = userProfile.UserRole

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

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
                Role = userProfile.getUserRole();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT);

            }
        });
    }
}
