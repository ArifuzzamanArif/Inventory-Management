package com.example.munnaf.inventorymanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    boolean doubleTap=false;

    private ImageView main_imageView;
    private TextView login_textView;
    private CardView manager, salesman;
    private TextView logoutButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_imageView=findViewById(R.id.main_imageView);


        manager=findViewById(R.id.manager);
        salesman=findViewById(R.id.salesman);
        login_textView=findViewById(R.id.login_textView);
        logoutButton=findViewById(R.id.logoutButton);

        mAuth = FirebaseAuth.getInstance();


        Animation mainImageAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_to_right_animation);
        main_imageView.setAnimation(mainImageAnimation);


        Animation loginTextAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink_animation);
        login_textView.setAnimation(loginTextAnimation);

        Animation logoutButtonAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_to_left_animation);
        logoutButton.setAnimation(logoutButtonAnimation);


        Animation managerCardAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_to_right_animation);
        manager.setAnimation(managerCardAnimation);

        Animation salesmanCardAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_to_left_animation);
        salesman.setAnimation(salesmanCardAnimation);


        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent managerLogin= new Intent(MainActivity.this, Manager_Login.class);
                startActivity(managerLogin);
            }
        });

        salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salesManLogin= new Intent(MainActivity.this, Salesman_Main.class);
                startActivity(salesManLogin);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
        }

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