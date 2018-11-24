package com.example.brandon.control.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.brandon.control.Delete.DeleteAssigned;
import com.example.brandon.control.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanDeleteAssign extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    EditText txtCodigo;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_delete_assigned);


    }

    public void ScnnerAssign(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(this);
        setContentView(scannerView);
        scannerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {
        String dato = result.getText();
        setContentView(R.layout.activity_scan_delete_assigned);
        scannerView.stopCamera();
        txtCodigo = (EditText) findViewById(R.id.IDASSIG);
        txtCodigo.setText(dato);

        Intent del = new Intent(getApplicationContext(),DeleteAssigned.class);
        del.putExtra("AssignedID",txtCodigo.getText().toString());
        startActivity(del);
    }
}
