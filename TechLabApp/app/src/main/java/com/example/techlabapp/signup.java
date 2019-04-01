package com.example.techlabapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        String email = getIntent().getStringExtra("email");
        TextView HR = (TextView)findViewById(R.id.HRemail);
    }
    public void  onSignUpClick(View v)
    {
        if(v.getId() == R.id.signup_button)
        {
            EditText name = (EditText)findViewById(R.id.HRname);
            EditText email = (EditText)findViewById(R.id.HRemail);
            EditText stdnr = (EditText)findViewById(R.id.HRnmr);
            EditText Password = (EditText)findViewById(R.id.HRpass1);
            EditText Confirm_Password = (EditText)findViewById(R.id.HRpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String stdnrstr = stdnr.getText().toString();
            String Passwordstr = Password.getText().toString();
            String Confirm_Passwordstr = Confirm_Password.getText().toString();

            if(!Passwordstr.equals(Confirm_Passwordstr))
            {
                //popup msg
                Toast pass = Toast.makeText(signup.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }

        }

    }
}
