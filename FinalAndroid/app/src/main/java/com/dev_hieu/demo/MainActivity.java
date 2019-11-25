package com.dev_hieu.demo;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dev_hieu.demo.camera.CameraSourcePreview;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import static com.dev_hieu.demo.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static Button btnSave, btn_flash_mode;


    private static BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        // getting barcode instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        barcodeReader.getFragmentManager();
        barcodeReader.onCreate(savedInstanceState);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Context context;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Information");
                alert.setView(R.layout.dialog_match_information);
                alert.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog.Builder builder;
                Dialog dialog = alert.create();
                dialog.show();
                break;
        }
    }
}
