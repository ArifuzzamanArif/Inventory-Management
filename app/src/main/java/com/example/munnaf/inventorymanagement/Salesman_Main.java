package com.example.munnaf.inventorymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Salesman_Main extends AppCompatActivity
{
    private ImageView salesmanMain_imageView;
    private TextView cameraTextView;

    private CardView salesman_Camera;

    private ImageButton buttonSubmit;
    private EditText productCode2;

    Firebase mRootRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman__main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        salesmanMain_imageView=findViewById(R.id.salesmanMain_imageView);
        cameraTextView=findViewById(R.id.cameraTextView);

        salesman_Camera=findViewById(R.id.salesman_Camera);

        buttonSubmit=findViewById(R.id.buttonSubmit);
        productCode2=findViewById(R.id.productCode2);


        Animation salesmanImageAnimation= AnimationUtils.loadAnimation(Salesman_Main.this, R.anim.left_to_right_animation);
        salesmanMain_imageView.setAnimation(salesmanImageAnimation);

        Animation cameraTextAnimation= AnimationUtils.loadAnimation(Salesman_Main.this, R.anim.blink_animation);
        cameraTextView.setAnimation(cameraTextAnimation);


        mRootRef2=new Firebase("https://inventory-management-b13aa.firebaseio.com/Product_Info");



        salesman_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Salesman_Main.this, Salesman_Scanning.class));

            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id= productCode2.getText().toString();

                Firebase childRef2= mRootRef2.child(id);

                if (!id.isEmpty()){

                    childRef2.child("status").setValue("Sold");

                    Toast.makeText(Salesman_Main.this, "Information Updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Salesman_Main.this, "Please Enter Product Code", Toast.LENGTH_LONG).show();
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