package com.example.arosaje;

import static android.hardware.camera2.CameraMetadata.LENS_FACING_BACK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arosaje.data.AppData;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class CameraAtivity extends AppCompatActivity {


    private ImageCapture imageCapture;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_PERMISSION_CODE = 10;
    private ImageView imageIV;

    private Button btCapture;
    private Button btnRetour;
    private ImageView ivCapture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_ativity);

        btCapture = findViewById(R.id.btCapture);
        btCapture.setOnClickListener(btCaptureListener);
        btnRetour = findViewById(R.id.btnAccepter);
        btnRetour.setOnClickListener(btnRetourListener);

        ivCapture = findViewById(R.id.ivCapture);

        // Capture Image:
        PreviewView previewView = findViewById(R.id.previewView);

        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);
        requestPermissionCamera();

        cameraProviderFuture.addListener(() -> {
            try {
                // Camera provider is now guaranteed to be available
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Set up the view finder use case to display camera preview
                Preview preview = new Preview.Builder().build();

                // Set up the capture use case to allow users to take photos
                imageCapture = new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build();

                // Choose the camera by requiring a lens facing
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(LENS_FACING_BACK)
                        .build();

                // Attach use cases to the camera with the same lifecycle owner
                Camera camera = cameraProvider.bindToLifecycle(
                        ((LifecycleOwner) this),
                        cameraSelector,
                        preview,
                        imageCapture);

                // Connect the preview use case to the previewView
                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider());
            } catch (InterruptedException | ExecutionException e) {
                // Currently no exceptions thrown. cameraProviderFuture.get()
                // shouldn't block since the listener is being called, so no need to
                // handle InterruptedException.
            }
        }, ContextCompat.getMainExecutor(this));

    }

    private View.OnClickListener btnRetourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    private View.OnClickListener btCaptureListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            long timestamp = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();
            String filename = "NEW_IMAGE-" + timestamp + ".jpg";
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

            Toast.makeText(CameraAtivity.this, "Clic!", Toast.LENGTH_SHORT).show();

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(
                    getContentResolver(),
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues).build();

            imageCapture.takePicture(outputFileOptions,
                    ContextCompat.getMainExecutor(view.getContext()),
                    new ImageCapture.OnImageSavedCallback() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                            Toast.makeText(CameraAtivity.this, "Photo has been saved successfully." + filename, Toast.LENGTH_LONG).show();

                            AppData appData = AppData.getInstance();
                            appData.imageTemp = "/storage/emulated/0/Pictures/" + filename;
                            File imgFile = new File(appData.imageTemp);

                            // on below line we are initializing variables with ids.
                            imageIV = (ImageView) findViewById(R.id.ivCapture);

                            // on below line we are checking if the image file exist or not.
                            if (imgFile.exists()) {
                                // on below line we are creating an image bitmap variable
                                // and adding a bitmap to it from image file.
                                Bitmap imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                                // on below line we are setting bitmap to our image view.
                                imageIV.setImageBitmap(imgBitmap);
                            } else {
                                Toast.makeText(CameraAtivity.this, "Photo not found!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onError(ImageCaptureException error) {
                            Toast.makeText(CameraAtivity.this, "Photo has been saved successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    };

    // Camera permission
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionCamera() {
        ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSION,
                CAMERA_PERMISSION_CODE
        );
    }
}