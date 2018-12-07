package com.example.munnaf.inventorymanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity
{
    boolean doubleTap=false;


    private ImageView login_imageView;
    private EditText loginUserName, loginPassword;
    private TextView loginButton, signUpButton,loginTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginUserName=findViewById(R.id.loginUserName);
        loginPassword=findViewById(R.id.loginPassword);
        loginButton=findViewById(R.id.loginButton);
        signUpButton=findViewById(R.id.signUpButton);
        loginTextView=findViewById(R.id.loginTextView);
        login_imageView=findViewById(R.id.login_imageView);


        mAuth = FirebaseAuth.getInstance();


        Animation loginTextAnimation= AnimationUtils.loadAnimation(Login.this, R.anim.blink_animation);
        loginTextView.setAnimation(loginTextAnimation);

        Animation loginImageAnimation= AnimationUtils.loadAnimation(Login.this, R.anim.left_to_right_animation);
        login_imageView.setAnimation(loginImageAnimation);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = loginUserName.getText().toString();
                String password = loginPassword.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(Login.this,MainActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this, "Please check Your Emai and Passwoed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleTap){
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press Again to Exit the App", Toast.LENGTH_SHORT).show();
            doubleTap=true;
            Handler handler= new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    doubleTap=false;
                }
            },500);
        }
    }
}