package com.example.brandon.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.brandon.control.Add.AddEquipment;
import com.example.brandon.control.Delete.Delete;
import com.example.brandon.control.Modify.Modify;
import com.example.brandon.control.Query.ConsultGeneral;
import com.example.brandon.control.Query.ConsultGeneralDevice;
import com.example.brandon.control.Query.ConsultaGeneralAssigned;
import com.example.brandon.control.Scanner.ScanAssingDevice;
import com.example.brandon.control.Query.Query;

public class MainActivity extends AppCompatActivity {

    Button btnAgregar;
    Button btnAsignar;
    Button btnConsultar;
    Button btnEliminar;
    Button btnScanner;
    Button btnModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAgregar = (Button)findViewById(R.id.Agregar);
        btnAsignar = (Button)findViewById(R.id.Asignar);
        btnConsultar = (Button)findViewById(R.id.Consul);
        btnEliminar = (Button)findViewById(R.id.Borrar);
        btnScanner = (Button)findViewById(R.id.Scanner);
        btnModificar = (Button)findViewById(R.id.Modificar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agregar = new Intent(getApplicationContext(), AddEquipment.class);
                startActivity(agregar);

            }
        });

        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent asignar = new Intent(getApplicationContext(), ScanAssingDevice.class);
                startActivity(asignar);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consult = new Intent(getApplicationContext(),Query.class);
                startActivity(consult);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borar = new Intent(getApplicationContext(),Delete.class);
                startActivity(borar);
            }
        });

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanner = new Intent(getApplicationContext(),ConsultGeneral.class);
                startActivity(scanner);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modificar = new Intent(getApplicationContext(),Modify.class);
                startActivity(modificar);
            }
        });
    }
}
