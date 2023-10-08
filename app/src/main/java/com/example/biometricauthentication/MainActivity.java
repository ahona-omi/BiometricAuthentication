package com.example.biometricauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    Button btnAuth;
    TextView tvAuthStatus;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        btnAuth = findViewById(R.id.btnAuth);
        tvAuthStatus = findViewById(R.id.tvAuthStatus);

        //Initialize executor for handling asynchronous tasks
        executor = ContextCompat.getMainExecutor(this);

        // Create a BiometricPrompt instance with callbacks for authentication events
        biometricPrompt = new BiometricPrompt(MainActivity.this,executor,new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode,@NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //if any error come with authentication
                tvAuthStatus.setText("Error  :"+errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Handle authentication get success
                tvAuthStatus.setText("Authentication Successful :)");
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //failed to Authentication
                tvAuthStatus.setText("Authentication Failed");
            }
        });

        // Configure the title, subtitle, and other settings for the authentication dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint or face")
                .setDeviceCredentialAllowed(true)       // Allow device credential fallback(Use pattern or Pin as device required)
                .build();

        // Set a click listener for the authentication button
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the biometric authentication process with the configured settings
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}