package com.dev_hieu.studentmatch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /**
     * @param savedInstanceState
     * @author dev_hieu
     * @date: 18/11/2019 - current time
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Scan QR");

    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

}
