package com.bluapp.androidview2.DeviceFeatures;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity17 extends AppCompatActivity {
    private TextView fpinfoTv;
    private CancellationSignal cancellationSignal;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features17);
        fpinfoTv = (TextView) findViewById(R.id.fpinfo);
        if(checkFPSupport()){
            authenticateUser();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void authenticateUser(){
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("AndroidView2 Biometric")
                .setSubtitle("Authentication is required to continue")
                .setDescription("This app show biometric authentication demo.")
                .setNegativeButton("Cancel", this.getMainExecutor(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fpinfoTv.setText("Authentication cancelled");
                            }
                        }).build();
        biometricPrompt.authenticate(getCancellationSignal(), getMainExecutor(), getAuthenticationCallback());
    }

    private Boolean checkFPSupport() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        PackageManager packageManager = this.getPackageManager();
        if (!keyguardManager.isKeyguardSecure()) {
            fpinfoTv.setText("Lock screen security not enabled in Settings");
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            fpinfoTv.setText("Fingerprint authentication permission not enabled");
            return false;
        }
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return true;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private BiometricPrompt.AuthenticationCallback getAuthenticationCallback() {
        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                fpinfoTv.setText("Authentication error: " + errString);
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                fpinfoTv.setText("Authentication Succeeded");
                super.onAuthenticationSucceeded(result);
            }
        };
    }

    private CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                fpinfoTv.setText("Cancelled via signal");
            }
        });
        return cancellationSignal;
    }


}

