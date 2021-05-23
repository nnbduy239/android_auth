
package com.iuh.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import android.os.Bundle;

public class MoodActivity extends AppCompatActivity {

    ImageButton imgbtnHappy, imgbtnSad, imgbtnNormal;
    TextView tv_name;
    Button btnFinish;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        tv_name = findViewById(R.id.tv_name);
        imgbtnHappy = findViewById(R.id.imgbtnHappy);
        imgbtnSad = findViewById(R.id.imgbtnSad);
        imgbtnNormal = findViewById(R.id.imgbtnNormal);
        btnFinish = findViewById(R.id.btnFinish);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mFirebaseUser.getUid());
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData userData = snapshot.getValue(UserData.class);
                assert userData != null;
                tv_name.setText(userData.getName());
                imgbtnHappy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int happy = Integer.parseInt(userData.getHappy().toString());
                        happy++;
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId",userData.getUserId());
                        hashMap.put("name",userData.getName());
                        hashMap.put("email",userData.getEmail());
                        hashMap.put("password",userData.getPassword());
                        hashMap.put("happy",happy+"");
                        hashMap.put("sad",userData.getSad());
                        hashMap.put("normal",userData.getNormal());
                        mDatabaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MoodActivity.this, "Hôm nay bạn thêm một Happy", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MoodActivity.this, "lỗi!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                imgbtnSad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int sad = Integer.parseInt(userData.getSad().toString());
                        sad++;
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId",userData.getUserId());
                        hashMap.put("name",userData.getName());
                        hashMap.put("email",userData.getEmail());
                        hashMap.put("password",userData.getPassword());
                        hashMap.put("happy",userData.getHappy());
                        hashMap.put("sad",sad+"");
                        hashMap.put("normal",userData.getNormal());
                        mDatabaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MoodActivity.this, "Hôm nay bạn thêm một sad", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MoodActivity.this, "lỗi!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                imgbtnNormal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int normal = Integer.parseInt(userData.getNormal().toString());
                        normal++;
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId",userData.getUserId());
                        hashMap.put("name",userData.getName());
                        hashMap.put("email",userData.getEmail());
                        hashMap.put("password",userData.getPassword());
                        hashMap.put("happy",userData.getHappy());
                        hashMap.put("sad",userData.getSad());
                        hashMap.put("normal",normal+"");
                        mDatabaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MoodActivity.this, "Hôm nay bạn thêm một normal", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MoodActivity.this, "lỗi!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MoodActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}