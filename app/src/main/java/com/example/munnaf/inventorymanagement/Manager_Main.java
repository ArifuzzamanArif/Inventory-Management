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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manager_Main extends AppCompatActivity
{
    private TextView product_info;
    private ImageView managerMain_imageView;

    private EditText productCode,productPrice, productColor,productDescription,productSize,productName;
    private CardView barcode_Scanner;
    private TextView save_Info, view_Info;
    private Spinner productType;

    private Product_Info productInfo;
    FirebaseDatabase database;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        product_info=findViewById(R.id.product_info);
        managerMain_imageView=findViewById(R.id.managerMain_imageView);

        productCode=findViewById(R.id.productCode);
        productName=findViewById(R.id.productName);
        productPrice=findViewById(R.id.productPrice);
        productColor=findViewById(R.id.productColor);
        productSize=findViewById(R.id.productSize);
        productType=findViewById(R.id.productType);
        productDescription=findViewById(R.id.productDescription);

        barcode_Scanner=findViewById(R.id.barcode_Scanner);
        save_Info=findViewById(R.id.save_Info);
        view_Info=findViewById(R.id.view_Info);


        productInfo= new Product_Info();
        database=FirebaseDatabase.getInstance();
        mRef=database.getReference().child("Product_Info");


        Animation managerMainImage_Animation = AnimationUtils.loadAnimation(Manager_Main.this, R.anim.left_to_right_animation);
        managerMain_imageView.setAnimation(managerMainImage_Animation);

        Animation managerCamera_Animation = AnimationUtils.loadAnimation(Manager_Main.this, R.anim.right_to_left_animation);
        barcode_Scanner.setAnimation(managerCamera_Animation);

        Animation productInfoAnimation = AnimationUtils.loadAnimation(Manager_Main.this, R.anim.blink_animation);
        product_info.setAnimation(productInfoAnimation);


        barcode_Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent manager_barcodeScan= new Intent(Manager_Main.this, Salesman_Scanning.class);
                startActivity(manager_barcodeScan);
            }
        });



        save_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean error = false;

                String code=productCode.getText().toString();
                if (code.isEmpty()) {
                    productCode.setError("Product Code is Empty");
                    error = true;
                }

                String name=productName.getText().toString();
                if (name.isEmpty()) {
                    productName.setError("Product Code is Empty");
                    error = true;
                }

                String color=productColor.getText().toString();
                if (color.isEmpty()) {
                    productColor.setError("Product Color is Empty");
                    error = true;
                }

                String description=productDescription.getText().toString();
                if (description.isEmpty()) {
                    productDescription.setError("Product Description is Empty");
                    error = true;
                }

                String price=productPrice.getText().toString();
                if (price.isEmpty()) {
                    productPrice.setError("Product Price is Empty");
                    error = true;
                }

                String size=productSize.getText().toString();
                if (size.isEmpty()) {
                    productSize.setError("Product Size is Empty");
                    error = true;
                }

                String type=productType.getSelectedItem().toString();


                productInfo.setCode(code);
                productInfo.setName(name);
                productInfo.setColor(color);
                productInfo.setDescription(description);
                productInfo.setPrice(price);
                productInfo.setSize(size);
                productInfo.setType(type);

                     if (error==true){

                         Toast.makeText(Manager_Main.this, "Information not inserted. Please enter all information", Toast.LENGTH_LONG).show();

                         }
                         else {

                         mRef.child(productInfo.getCode()).setValue(productInfo);
                         Toast.makeText(Manager_Main.this, "Information Inserted", Toast.LENGTH_LONG).show();

                         }

                }


        });



        view_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewInfo=new Intent(Manager_Main.this,View_Information.class);
                startActivity(viewInfo);
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