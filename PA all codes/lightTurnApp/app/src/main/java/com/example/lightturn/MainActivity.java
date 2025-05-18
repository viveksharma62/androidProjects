    package com.example.lightturn;

    import android.content.Context;
    import android.hardware.camera2.CameraAccessException;
    import android.hardware.camera2.CameraManager;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Toast;
    import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {

        private CameraManager cameraManager;
        private String cameraId;
        private boolean isTorchOn = false;  // Keeps track of the torch status
        private Button buttonTorch;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Get the reference to the button
            buttonTorch = findViewById(R.id.buttonTorch);
            Toast.makeText(MainActivity.this,"created by vivek sharma" , Toast.LENGTH_SHORT).show();

            // Initialize CameraManager
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

            try {
                // Get the camera ID for the device's rear camera with flash
                cameraId = cameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            // Set a click listener to toggle flashlight
            buttonTorch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTorchOn) {
                        // Turn off the flashlight
                        turnOffFlashLight();
                    } else {
                        // Turn on the flashlight
                        turnOnFlashLight();
                    }
                }
            });
        }

        // Method to turn on the flashlight
        private void turnOnFlashLight() {
            try {
                if (cameraManager != null) {
                    cameraManager.setTorchMode(cameraId, true);  // Enable the torch mode
                    isTorchOn = true;
                    buttonTorch.setText("TURN OFF");
                    Toast.makeText(MainActivity.this, "Flashlight ON", Toast.LENGTH_SHORT).show();
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Failed to turn on the flashlight", Toast.LENGTH_SHORT).show();
            }
        }

        // Method to turn off the flashlight
        private void turnOffFlashLight() {
            try {
                if (cameraManager != null) {
                    cameraManager.setTorchMode(cameraId, false);  // Disable the torch mode
                    isTorchOn = false;
                    buttonTorch.setText("TURN ON");
                    Toast.makeText(MainActivity.this, "Flashlight OFF", Toast.LENGTH_SHORT).show();
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Failed to turn off the flashlight", Toast.LENGTH_SHORT).show();
            }
        }
    }
