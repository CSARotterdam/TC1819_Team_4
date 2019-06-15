package com.example.mainmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;

public class Manage_Roles extends AppCompatActivity {

    private EditText SearchInput;
    private ImageButton SearchButton;

    private RecyclerView Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_roles);

        SearchInput = (EditText) findViewById(R.id.SearchInput);
        SearchButton = (ImageButton) findViewById(R.id.SearchButton);

        Results = (RecyclerView) findViewById(R.id.rolesRecyclerView);
    }

}
