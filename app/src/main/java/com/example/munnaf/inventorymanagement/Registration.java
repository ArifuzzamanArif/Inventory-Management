package com.example.munnaf.inventorymanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity
{
    private ImageView signUp_imageView;
    private TextView signUpTextView;

    private EditText signUpUserName, signUpPassword;
    private TextView signUpButton_2, loginButton_2;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signUpTextView = findViewById(R.id.signUpTextView);
        signUp_imageView = findViewById(R.id.signUp_imageView);

        signUpUserName = findViewById(R.id.signUpUserName);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpButton_2 = findViewById(R.id.signUpButton_2);
        loginButton_2 = findViewById(R.id.loginButton_2);


        mAuth = FirebaseAuth.getInstance();


        Animation signUpImageAnimation= AnimationUtils.loadAnimation(Registration.this, R.anim.left_to_right_animation);
        signUp_imageView.setAnimation(signUpImageAnimation);

        Animation signUpTextAnimation= AnimationUtils.loadAnimation(Registration.this, R.anim.blink_animation);
        signUpTextView.setAnimation(signUpTextAnimation);



        loginButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

        signUpButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = signUpUserName.getText().toString();
                String password = signUpPassword.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Registration.this, "Registration Successful...", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registration.this,MainActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })  ;
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}