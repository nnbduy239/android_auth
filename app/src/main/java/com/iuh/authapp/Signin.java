package com.iuh.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {
    EditText pl_emailS,pl_passS;
    Button btnSigninS;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mFirebaseAuth=FirebaseAuth.getInstance();

        pl_emailS = findViewById(R.id.leesinS);
        pl_passS = findViewById(R.id.lucianS);
        btnSigninS = findViewById(R.id.btnSigninS);

        btnSigninS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = pl_emailS.getText().toString();
                String password = pl_passS.getText().toString();
                if (email == null || email.trim().length() == 0){
                    Toast.makeText(Signin.this, "Chưa nhập email", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signin.this, "Chưa nhập password", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(email,password);
                }
            }
        });
    }

    private void login(String email, String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Signin.this,MoodActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Signin.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}