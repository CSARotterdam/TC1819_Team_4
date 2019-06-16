package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class RULAdapter extends RecyclerView.Adapter<RULAdapter.MyViewHolder> {

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

    Context context;

    public RULAdapter(Context context, ArrayList<String> userBday, ArrayList<String> userClass, ArrayList<String> userEmail, ArrayList<String> userID, ArrayList<String> UserName, ArrayList<String> UserSurname, ArrayList<String> UserNickName, ArrayList<String> UserPhone, ArrayList<String> UserRole, ArrayList<HashMap<String,Object>> UserItemsBorrowed, ArrayList<HashMap<String,Object>> UserItemsReserved, ArrayList<String> Uids) {
        this.context = context;
        this.userBday = userBday;
        this.userClass = userClass;
        this.userEmail = userEmail;
        this.userID = userID;
        this.UserName = UserName;
        this.UserSurname = UserSurname;
        this.UserNickName = UserNickName;
        this.UserPhone = UserPhone;
        this.UserRole = UserRole;
        this.UserItemsBorrowed = UserItemsBorrowed;
        this.UserItemsReserved = UserItemsReserved;
        this.Uids = Uids;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rullistlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.person.setText(UserName.get(position)+" "+UserSurname.get(position));
        holder.userclass.setText(userClass.get(position));
        if (UserItemsReserved.get(position) != null){
            holder.reserving.setText("User has reserved "+ UserItemsReserved.get(position).size()+" item(s).");
        }
        else {
            holder.reserving.setText("User has reserved 0 item(s).");
        }
        if (UserItemsBorrowed.get(position) != null){
            holder.borrowing.setText("User has borrowed "+ UserItemsBorrowed.get(position).size()+" item(s).");
        }
        else {
            holder.borrowing.setText("User has borrowed 0 item(s).");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent ruluser = new Intent(view.getContext(), RULuser.class);
            ruluser.putExtra("UserName", UserName.get(position));
            ruluser.putExtra("UserSurname", UserSurname.get(position));
            ruluser.putExtra("UserClass", userClass.get(position));
            ruluser.putExtra("UserItemsBorrowed", UserItemsBorrowed.get(position));
            ruluser.putExtra("UserItemsReserved", UserItemsReserved.get(position));
            ruluser.putExtra("Uid", Uids.get(position));
            context.startActivity(ruluser);
            }
        });
    }


    @Override
    public int getItemCount() {
        return UserName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView person, userclass, reserving, borrowing;

        public MyViewHolder(View itemView) {
            super(itemView);

            person = itemView.findViewById(R.id.person);
            userclass = itemView.findViewById(R.id.userclass);
            reserving = itemView.findViewById(R.id.reserving);
            borrowing = itemView.findViewById(R.id.borrowing);

        }
    }
}