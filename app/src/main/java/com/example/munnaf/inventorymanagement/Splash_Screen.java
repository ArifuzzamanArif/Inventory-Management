package com.example.munnaf.inventorymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class Splash_Screen extends AppCompatActivity
{

    private ImageView splashImageView;
    private TextView splashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        splashImageView= findViewById(R.id.splashImageView);
        splashTextView= findViewById(R.id.splashTextView);

        //Animation for image
        Animation splashImageAnimation= AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.blink_animation_2);
        splashImageView.setAnimation(splashImageAnimation);

        //Animation for Text
        Animation splashButtonAnimation= AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.buttom_to_top_animation);
        splashTextView.setAnimation(splashButtonAnimation);



        final Intent splashIntent= new Intent(Splash_Screen.this, MainActivity.class);

        Thread mythread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                } finally {
                    startActivity(splashIntent);
                    finish();
                }
            }
        });

        mythread.start();
    }
}