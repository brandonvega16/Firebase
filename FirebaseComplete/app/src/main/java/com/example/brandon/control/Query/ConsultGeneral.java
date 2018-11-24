package com.example.brandon.control.Query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.brandon.control.R;

public class ConsultGeneral extends AppCompatActivity {

    Button btnDev,btnAsig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_general);

        btnDev = (Button)findViewById(R.id.ConsultDevice);
        btnAsig = (Button)findViewById(R.id.ConsultAssigned);

        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dev = new Intent(getApplicationContext(),ConsultGeneralDevice.class);
                startActivity(dev);
            }
        });

        btnAsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent asig = new Intent(getApplicationContext(),ConsultaGeneralAssigned.class);
                startActivity(asig);
            }
        });
    }
}
