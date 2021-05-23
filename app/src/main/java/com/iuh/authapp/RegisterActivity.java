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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText pl_emailR,pl_nameR, pl_passwordR,pl_confirmPassR;
    Button btnRegisterR;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        pl_confirmPassR = findViewById(R.id.pl_conpasswod);
        pl_emailR = findViewById(R.id.pl_emailR);
        pl_nameR = findViewById(R.id.pl_nameR);
        pl_passwordR = findViewById(R.id.pl_passwod);
        btnRegisterR = findViewById(R.id.btnSigninS);

        btnRegisterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = pl_nameR.getText().toString();
                final String email = pl_emailR.getText().toString();
                final String password = pl_passwordR.getText().toString();
                final String confirmPassword = pl_confirmPassR.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(RegisterActivity.this, "Điền đầy đủ thông tin vào tất cả các ô", Toast.LENGTH_SHORT).show();
                }
                else if(password.equalsIgnoreCase(confirmPassword)==false){
                    Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    pl_confirmPassR.selectAll();
                } else{
                    register(name, email, password);
                }
            }
        });



    }

    private void register(String name, String email, String password) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser rUser = mFirebaseAuth.getCurrentUser();
                    String userId = rUser.getUid();
                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);//sai

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId",userId);
                    hashMap.put("name",name);
                    hashMap.put("email",email);
                    hashMap.put("password",password);
                    hashMap.put("happy","0");
                    hashMap.put("sad","0");
                    hashMap.put("normal","0");

                    mDatabaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException().getMessage()),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException().getMessage()),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}