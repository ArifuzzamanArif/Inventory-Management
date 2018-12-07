package com.example.munnaf.inventorymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;

public class Salesman_Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private ZXingScannerView ScannerView;

    private static final int REQUEST_CAMERA = 1;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ScannerView =new ZXingScannerView(Salesman_Scanning.this);
        setContentView(ScannerView);
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        result.getText();
        result.getBarcodeFormat().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(Salesman_Scanning.this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ScannerView.resumeCameraPreview(Salesman_Scanning.this);
            }
        });

        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

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