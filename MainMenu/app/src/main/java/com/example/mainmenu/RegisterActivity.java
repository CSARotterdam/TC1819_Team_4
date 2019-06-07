package com.example.mainmenu;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNickName, userPassword, userEmail, userID, userClass, userName,userSurname, userPhone, userBday ;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
<<<<<<< HEAD
    String email, Username, bday, password, name, surname, id, phone, Class, Role ;
=======
    String email, Username, bday, password, name, surname, id, phone, Class ;
    ArrayList<String> Borrowlist;
>>>>>>> master


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    //upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivityActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });


                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (RegisterActivity.this, LoginActivityActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userNickName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userID = (EditText)findViewById(R.id.etID);
        userClass = (EditText)findViewById(R.id.etClass);
        userName = (EditText)findViewById(R.id.etName);
        userPhone = (EditText)findViewById(R.id.etPhone);
        userSurname = (EditText)findViewById(R.id.etSurName);
        userBday = (EditText)findViewById(R.id.etBday);
    }


    private boolean validate(){
        Boolean result = false;


        Username = userNickName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        name = userName.getText().toString();
        surname = userSurname.getText().toString();
        bday = userBday.getText().toString();
        id = userID.getText().toString();
        phone = userPhone.getText().toString();
        Class = userClass.getText().toString();
        Borrowlist = new ArrayList<>();




        if(Username.isEmpty() || password.isEmpty() || email.isEmpty() ){
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
<<<<<<< HEAD
        userProfile userProfile = new userProfile(name,surname,Username,bday,email,id,phone,Class, Role);
=======
        userProfile userProfile = new userProfile(name,surname,Username,bday,email,id,phone,Class,Borrowlist);
>>>>>>> master
        myRef.setValue(userProfile);

    }





  //end of file
}



