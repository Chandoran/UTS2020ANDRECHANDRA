package com.example.uts_amub_ti7a_1711500007_andrechandra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterAct extends AppCompatActivity {
    ImageView wut;
    Button next,back;
    EditText username, password, email;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        next = findViewById(R.id.next);
        //back = findViewById(R.id.login);

        //berpindah dari activity lain ke activity registered


        //menyimpan data kepada localstorage (handphone)


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key,username.getText().toString()); //mengambil data inputan username disimpan ke variabel username_key
                editor.apply();

                //proses SIMPAN KE DATABASE FIREBASE
                reference = FirebaseDatabase.getInstance().getReference().child("User/Andre");
                //.child(usernamer.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                        //dataSnapshot.getRef().child("saldo").setValue(100000);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //berpindah dari activity lain ke activity loginact
        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotologin = new Intent(RegisterAct.this, RegisterTwoAct.class);
                startActivity(gotologin);
            }
        }); */
                Intent gotonextregister = new Intent(RegisterAct.this, RegisterTwoAct.class);
                startActivity(gotonextregister);
            }
        });

    }
}
