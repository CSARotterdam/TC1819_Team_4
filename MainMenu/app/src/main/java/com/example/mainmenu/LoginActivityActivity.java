
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String userRoleAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setupUIViews();

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etUserPassword);
        Login = (Button) findViewById(R.id.btnLogin);

        userProfile profile = new userProfile();
        profile.setUserRole("User");
        userRoleAdmin = (profile.getUserRole());

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        if (user != null) {
            finish();
            startActivity(new Intent(LoginActivityActivity.this, MainActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Email.getText().toString().isEmpty()) && (Password.getText().toString().isEmpty())) {
                    return;
                } else {

                    validate(Email.getText().toString(), Password.getText().toString());
                }
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityActivity.this, RegisterActivity.class));
            }
        });


    }

    private void validate(String userName, String userPassword) {
        progressDialog.setMessage("Logging In");
        progressDialog.show();
        if ((Email.getText().toString().isEmpty()) || (Password.getText().toString().isEmpty())) {
            Toast.makeText(LoginActivityActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else {
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    final AuthResult taskResult = task.getResult();
                    FirebaseUser user = task.getResult().getUser();
                    String uid = user.getUid();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(uid);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userProfile profile = dataSnapshot.getValue(userProfile.class);
                            String role = profile.getUserRole();

                            if (role.equals("Admin")) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivityActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                AuthResult test = taskResult;
                                startActivity(new Intent(LoginActivityActivity.this, MainActivityAdmin.class));
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivityActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivityActivity.this, MainActivity.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(LoginActivityActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT);

                        }
                    });


                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivityActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });

        }
    }


    private void setupUIViews() {
        Register = (TextView) findViewById(R.id.tvRegister);
    }

}


