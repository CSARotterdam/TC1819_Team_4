package com.example.mainmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ReserveUserlist extends AppCompatActivity{

    ArrayList<String> userBday = new ArrayList<>();
    ArrayList<String> userClass = new ArrayList<>();
    ArrayList<String> userEmail = new ArrayList<>();
    ArrayList<String> userID = new ArrayList<>();
    ArrayList<String> UserName = new ArrayList<>();
    ArrayList<String> UserSurname = new ArrayList<>();
    ArrayList<String> UserNickName = new ArrayList<>();
    ArrayList<String> UserPhone = new ArrayList<>();
    ArrayList<String> UserRole = new ArrayList<>();
    ArrayList<String> Uids = new ArrayList<>();
    ArrayList<HashMap<String,Object>> UserItemsBorrowed = new ArrayList<>();
    ArrayList<HashMap<String,Object>> UserItemsReserved = new ArrayList<>();

    public String newuserBday;
    public String newuserClass;
    public String newuserEmail;
    public String newuserID;
    public String newUserName;
    public String newUserSurname;
    public String newUserNickName;
    public String newUserPhone;
    public String newUserRole;
    public String Uid;
    public HashMap<String,Object> newUserItemsBorrowed;
    public HashMap<String,Object> newUserItemsReserved;

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserveuserlist);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView5);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userBday.clear();
                userClass.clear();
                userEmail.clear();
                userID.clear();
                UserName.clear();
                UserSurname.clear();
                UserNickName.clear();
                UserPhone.clear();
                UserRole.clear();
                UserItemsBorrowed.clear();
                UserItemsReserved.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Uid = dataSnapshot1.getKey();
                    userProfile userProfile = dataSnapshot1.getValue(userProfile.class);
                    newuserBday = userProfile.getUserBday();
                    newuserClass = userProfile.getUserClass();
                    newuserEmail = userProfile.getUserEmail();
                    newuserID = userProfile.getUserID();
                    newUserName = userProfile.getUserName();
                    newUserSurname = userProfile.getUserSurname();
                    newUserNickName = userProfile.getUserNickName();
                    newUserPhone = userProfile.getUserPhone();
                    newUserRole = userProfile.getUserRole();
                    newUserItemsBorrowed = userProfile.getItemsBorrowed();
                    newUserItemsReserved = userProfile.getItemsReserved();

                    userBday.add(newuserBday);
                    userClass.add(newuserClass);
                    userEmail.add(newuserEmail);
                    userID.add(newuserID);
                    UserName.add(newUserName);
                    UserSurname.add(newUserSurname);
                    UserNickName.add(newUserNickName);
                    UserPhone.add(newUserPhone);
                    UserRole.add(newUserRole);
                    UserItemsBorrowed.add(newUserItemsBorrowed);
                    UserItemsReserved.add(newUserItemsReserved);
                    Uids.add(Uid);
                }
                RULAdapter RULAdapter = new RULAdapter(ReserveUserlist.this, userBday, userClass, userEmail, userID, UserName, UserSurname, UserNickName, UserPhone, UserRole, UserItemsBorrowed, UserItemsReserved, Uids);
                recyclerView.setAdapter(RULAdapter);
                RULAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button back = findViewById(R.id.rul_backtomenu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}
