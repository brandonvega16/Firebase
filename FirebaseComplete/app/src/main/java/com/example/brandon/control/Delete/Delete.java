package com.example.brandon.control.Delete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.brandon.control.R;
import com.example.brandon.control.Scanner.ScanDeleteAssign;
import com.example.brandon.control.Scanner.ScanDeleteDevice;

public class Delete extends AppCompatActivity {

    Button btnDev;
    Button btnAsig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btnDev = (Button)findViewById(R.id.BajaEquipo);
        btnAsig = (Button)findViewById(R.id.BajaAsignado);

        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bajaDev = new Intent(getApplicationContext(),ScanDeleteDevice.class);
                startActivity(bajaDev);
            }
        });

        btnAsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bajaAsig = new Intent(getApplicationContext(),ScanDeleteAssign.class);
                startActivity(bajaAsig);
            }
        });
    }
}
