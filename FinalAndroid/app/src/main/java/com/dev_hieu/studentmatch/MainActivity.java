package com.dev_hieu.studentmatch;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev_hieu.studentmatch.camera.CameraSourcePreview;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static String smg = "";

    TextView txtBarcode;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private CameraSourcePreview preview;
    private TextRecognizer textRecognizer;


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

        txtBarcode = findViewById(R.id.txtBarcode);
        preview = findViewById(R.id.cameraSourcePreview);

        setupBarCodeDetector();
        setupCameraSource();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    private void startCameraSource() {

        if (cameraSource != null) {

            try {
                preview.start(cameraSource);

            } catch (IOException e) {
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    private void setupBarCodeDetector() {
        Context context;
        barcodeDetector = new BarcodeDetector.Builder(this)
                .build();

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {

                    Log.i(TAG, "receiveDetections: " + barcodes.valueAt(0).displayValue);
                    txtBarcode.post(new Runnable() {
                        @Override
                        public void run() {
                            txtBarcode.setText(barcodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });

        if (!barcodeDetector.isOperational()) {
            Log.w(TAG, "Detector dependencies are not yet available.");
        }

        textRecognizer = new TextRecognizer.Builder(this).build();

        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies are not yet available.");
        }

        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();

                if (items.size() != 0) {
                    txtBarcode.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder str = new StringBuilder();
                            for (int i = 0; i < items.size(); i++) {
                                TextBlock item = items.valueAt(i);
                                str.append(item.getValue());
                                str.append("/n");
                            }
                            txtBarcode.setText(str.toString());
                        }
                    });
                }
            }
        });
    }

    private void setupCameraSource() {
        Context context;
        Detector detector;
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 640)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(30.0f)
                .build();
    }

    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

}
