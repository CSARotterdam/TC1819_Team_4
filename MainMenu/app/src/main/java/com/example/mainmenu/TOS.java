package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class TOS extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tos);

        TextView TOS_textfile = (TextView)findViewById(R.id.tos_text);
        InputStream inputStream = getResources().openRawResource(R.raw.tosfile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String TOS_text = "";
        int in;
        try {
            in = inputStream.read();
            while (in != -1)
            {
                byteArrayOutputStream.write(in);
                in = inputStream.read();
            }
            inputStream.close();
            TOS_text = byteArrayOutputStream.toString();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        TOS_textfile.setText(TOS_text);

        Button back = findViewById(R.id.tos_backtomenu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}

