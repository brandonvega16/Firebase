package com.example.brandon.control.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.brandon.control.Modify.ModifyAssigned;
import com.example.brandon.control.Modify.ModifyEquipment;
import com.example.brandon.control.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanModAssig extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    EditText txtCodigo;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_mod_assig);


    }

    public void Scnner(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(this);
        setContentView(scannerView);
        scannerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {
        String dato = result.getText();
        setContentView(R.layout.activity_scan_mod_assig);
        scannerView.stopCamera();
        txtCodigo = (EditText) findViewById(R.id.ScannerId);
        txtCodigo.setText(dato);

        Intent mod = new Intent(getApplicationContext(),ModifyAssigned.class);
        mod.putExtra("IdAssig",txtCodigo.getText().toString());
        startActivity(mod);
    }
}
