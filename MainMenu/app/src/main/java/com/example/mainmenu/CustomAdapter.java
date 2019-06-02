package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<listItem>ListItem;

    public CustomAdapter(List<listItem>ListItem) {
        this.ListItem = ListItem;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder
}