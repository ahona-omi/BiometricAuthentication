package com.example.biometricauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create a delay using a Handler to show the splash screen for 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to navigate from SplashActivity to MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                // Start the MainActivity
                startActivity(intent);

                // Finish the SplashActivity to prevent going back to it
                finish();
            }
        },2000);        // Delay for 2000 milliseconds (2 seconds)
    }
}