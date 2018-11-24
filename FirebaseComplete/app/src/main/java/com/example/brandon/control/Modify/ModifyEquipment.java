package com.example.brandon.control.Modify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.brandon.control.Model.Device;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyEquipment extends AppCompatActivity {

    EditText txtid,txtModelo,txtSerie;
    Spinner spOs,spHdd,spRam;
    Button btnSend,btnBusqueda;
    Firebase firebase;
    ArrayAdapter<CharSequence> os,hdd,ram;
    ListView list;
    private List<Device> listDevice = new ArrayList<Device>();
    ArrayAdapter adapter;
    Device equipoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_device);

        txtid = (EditText)findViewById(R.id.IDMOD);
        txtModelo = (EditText)findViewById(R.id.Model);
        txtSerie = (EditText)findViewById(R.id.Serie);
        spOs = (Spinner) findViewById(R.id.SpOS);
        spRam =(Spinner) findViewById(R.id.SpRAM);
        spHdd = (Spinner) findViewById(R.id.SpHDD);
        btnSend = (Button)findViewById(R.id.Mod);
        btnBusqueda = (Button)findViewById(R.id.ModConsulta);
        list = (ListView)findViewById(R.id.ListaEq);

        os = ArrayAdapter.createFromResource(this, R.array.OS, android.R.layout.simple_spinner_item);
        spOs.setAdapter(os);

        hdd = ArrayAdapter.createFromResource(this, R.array.Hdd, android.R.layout.simple_spinner_item);
        spHdd.setAdapter(hdd);

        ram = ArrayAdapter.createFromResource(this, R.array.Ram, android.R.layout.simple_spinner_item);
        spRam.setAdapter(ram);


        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                equipoSeleccionado = (Device) parent.getItemAtPosition(position);
                txtid.setText(equipoSeleccionado.getId());
                txtModelo.setText(equipoSeleccionado.getModelo());
                txtSerie.setText(equipoSeleccionado.getSerie());
                spOs.setAdapter(os);
            }
        });

        Bundle extra = getIntent().getExtras();
        String code = extra.getString("idDevice");
        txtid.setText(code);

        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtid.getText().toString();
                Query query = firebase.child(getString(R.string.nodo_Equipos)).orderByChild("id").equalTo(id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont = 0;
                        listDevice.clear();
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            cont ++;
                            Toast.makeText(ModifyEquipment.this,"Registros Encontrdos: " + cont, Toast.LENGTH_LONG).show();
                            Device equipos = snapshot.getValue(Device.class);
                            listDevice.add(equipos);
                            adapter = new ArrayAdapter<Device>(ModifyEquipment.this,android.R.layout.simple_list_item_1,listDevice);
                            list.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mOs = spOs.getSelectedItem().toString();
                String mRam = spRam.getSelectedItem().toString();
                String mHdd = spHdd.getSelectedItem().toString();
                if (!TextUtils.isEmpty(txtid.getText().toString()) || !TextUtils.isEmpty(txtModelo.getText().toString()))
                {
                    Device device = new Device();
                    device.setId(txtid.getText().toString().trim());
                    device.setModelo(txtModelo.getText().toString().trim());
                    device.setSerie(txtSerie.getText().toString().trim());
                    device.setOS(mOs.trim());
                    device.setHdd(mHdd.trim());
                    device.setRam(mRam.trim());
                    firebase.child(getString(R.string.nodo_Equipos)).child(device.getId()).setValue(device);
                }


            }
        });

    }
}
