package com.example.brandon.control.Add;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.brandon.control.Model.Device;
import com.example.brandon.control.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddEquipment extends AppCompatActivity {

    EditText txtModelo,txtSerie;
    Spinner spOs,spHdd,spRam;
    Button btnSend;
    Firebase firebase;
    FirebaseDatabase firebaseDatabase;
    ArrayAdapter<CharSequence> os,hdd,ram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        txtModelo = (EditText)findViewById(R.id.Modelo);
        txtSerie = (EditText)findViewById(R.id.Serie);
        spOs = (Spinner) findViewById(R.id.spOS);
        spRam =(Spinner) findViewById(R.id.spRam);
        spHdd = (Spinner) findViewById(R.id.spHdd);
        btnSend = (Button)findViewById(R.id.Enviar);

        os = ArrayAdapter.createFromResource(this, R.array.OS, android.R.layout.simple_spinner_item);
        spOs.setAdapter(os);

        hdd = ArrayAdapter.createFromResource(this, R.array.Hdd, android.R.layout.simple_spinner_item);
        spHdd.setAdapter(hdd);

        ram = ArrayAdapter.createFromResource(this, R.array.Ram, android.R.layout.simple_spinner_item);
        spRam.setAdapter(ram);


        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB)).child(getString(R.string.nodo_Equipos));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String mModelo = txtModelo.getText().toString();
                 String mSerie = txtSerie.getText().toString();
                 String mOs = spOs.getSelectedItem().toString();
                 String mRam = spRam.getSelectedItem().toString();
                 String mHdd = spHdd.getSelectedItem().toString();

                if (!TextUtils.isEmpty(mModelo) || !TextUtils.isEmpty(mSerie))
                {

                    Device device = new Device();
                    String mId = firebase.push().getKey();
                    device.setId(mId);
                    device.setModelo(mModelo);
                    device.setSerie(mSerie);
                    device.setOS(mOs);
                    device.setHdd(mHdd);
                    device.setRam(mRam);
                    firebase.child(device.getId()).setValue(device);F

                    txtModelo.setText("");
                    txtSerie.setText("");
                    spOs.setAdapter(os);
                    spRam.setAdapter(ram);
                    spHdd.setAdapter(hdd);
                    Toast.makeText(getApplicationContext(),"Registro Correcto",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Campos Vacios",Toast.LENGTH_LONG).show();
                }


            }
        });


    }


}
