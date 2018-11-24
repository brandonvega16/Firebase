package com.example.brandon.control.Query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.brandon.control.Scanner.ScanConsultDevice;
import com.example.brandon.control.Scanner.ScanConsultAssign;
import com.example.brandon.control.R;

public class Query extends AppCompatActivity {

    Button btnDevice;
    Button btnAsig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        btnDevice = (Button)findViewById(R.id.Equipos);
        btnAsig = (Button)findViewById(R.id.Asignados);

        btnDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent device = new Intent(getApplicationContext(),ScanConsultDevice.class);
                startActivity(device);
            }
        });

        btnAsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent asig = new Intent(getApplicationContext(),ScanConsultAssign.class);
                startActivity(asig);
            }
        });

    }
}
