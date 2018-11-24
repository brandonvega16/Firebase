package com.example.brandon.control.Modify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.brandon.control.R;
import com.example.brandon.control.Scanner.ScanAssingDevice;
import com.example.brandon.control.Scanner.ScanModAssig;

public class Modify extends AppCompatActivity {

    Button btnModEq;
    Button btnModAs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btnModEq = (Button)findViewById(R.id.ModEQ);
        btnModAs = (Button)findViewById(R.id.ModAssig);

        btnModEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modEq = new Intent(getApplicationContext(),ScanAssingDevice.class);
                startActivity(modEq);
            }
        });

        btnModAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modAsig = new Intent(getApplicationContext(),ScanModAssig.class);
                startActivity(modAsig);
            }
        });
    }
}
