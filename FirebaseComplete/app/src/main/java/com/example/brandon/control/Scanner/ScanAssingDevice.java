package com.example.brandon.control.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.brandon.control.Add.AssignEquipment;
import com.example.brandon.control.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanAssingDevice extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    EditText txtCodigo;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_assign);


    }

    public void ScnnerCod(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(this);
        setContentView(scannerView);
        scannerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {
        String dato = result.getText();
        setContentView(R.layout.activity_scan_assign);
        scannerView.stopCamera();
        txtCodigo = (EditText) findViewById(R.id.Codigo);
        txtCodigo.setText(dato);
        Intent consult = new Intent(getApplicationContext(),AssignEquipment.class);
        consult.putExtra("idDevice",txtCodigo.getText().toString());
        startActivity(consult);
    }
}
