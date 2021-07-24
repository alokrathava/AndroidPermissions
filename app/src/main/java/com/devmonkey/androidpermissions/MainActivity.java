package com.devmonkey.androidpermissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.devmonkey.androidpermissions.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final Context context = this;
    private final int REQUEST_CODE = 1;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        binding = ActivityMainBinding.inflate ( getLayoutInflater ( ) );
        setContentView ( binding.getRoot ( ) );

        init ( );
    }

    private void init () {
        if (ContextCompat.checkSelfPermission ( context , Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission ( context , Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission ( context , Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission ( context , Manifest.permission.READ_CALL_LOG ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission ( context , Manifest.permission.WRITE_CALL_LOG ) != PackageManager.PERMISSION_GRANTED) {

            requestPermission ( );

        } else {
            binding.val.setText ( "Permission Granted" );
        }

    }

    private void requestPermission () {
        ActivityCompat.requestPermissions ( MainActivity.this ,
                new String[]{
                        Manifest.permission.CAMERA ,
                        Manifest.permission.ACCESS_FINE_LOCATION ,
                        Manifest.permission.ACCESS_COARSE_LOCATION ,
                        Manifest.permission.READ_CALL_LOG ,
                        Manifest.permission.WRITE_CALL_LOG} ,
                REQUEST_CODE );
    }

    
    @Override
    public void onRequestPermissionsResult ( int requestCode , String permissions[] , int[] grantResults ) {
        super.onRequestPermissionsResult ( requestCode , permissions , grantResults );

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.val.setText ( "Permission Granted" );
            } else {
                binding.val.setText ( "Permission Denied" );
            }
        }
    }

}