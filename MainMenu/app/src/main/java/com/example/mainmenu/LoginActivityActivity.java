package com.example.mainmenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivityActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setupUIViews();

        Name =(EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etUserPassword);
        Login = (Button)findViewById(R.id.btnLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        if(user != null){
            finish();
            startActivity(new Intent(LoginActivityActivity.this, MainActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Name.getText().toString().isEmpty()) && (Password.getText().toString().isEmpty()) ){
                    return;
                }else{
                    validate(Name.getText().toString(), Password.getText().toString());
                }
            }
        });




       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent (LoginActivityActivity.this, RegisterActivity.class));
           }
       });


    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Logging In");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivityActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivityActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivityActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    }
                }

        });


    }

        private void setupUIViews(){
            Register = (TextView)findViewById(R.id.tvRegister);
        }

}


