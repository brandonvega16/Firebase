package com.example.brandon.control.Delete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class DeleteDevice extends AppCompatActivity {

    EditText txtIdDev;
    Button btnEliminar,btnBuscarDev;
    ListView ListDev;
    private List<Device> listDevice = new ArrayList<Device>();
    ArrayAdapter adapter;
    Device equipoSeleccionado;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_device);

        txtIdDev = (EditText)findViewById(R.id.txtEliminar);
        ListDev = (ListView)findViewById(R.id.ListDev);
        btnEliminar = (Button)findViewById(R.id.Eliminar);
        btnBuscarDev = (Button)findViewById(R.id.BuscarEq);
        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));

        ListDev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                equipoSeleccionado = (Device) parent.getItemAtPosition(position);
                txtIdDev.setText(equipoSeleccionado.getId());
            }
        });

        Bundle extra = getIntent().getExtras();
        String codeEq = extra.getString("DevID");
        txtIdDev.setText(codeEq);

        btnBuscarDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdEq = txtIdDev.getText().toString();
                Query query = firebase.child(getString(R.string.nodo_Equipos)).orderByChild("id").equalTo(IdEq);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont = 0;
                        listDevice.clear();
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            cont ++;
                            Toast.makeText(DeleteDevice.this,"Registros Encontrdos: " + cont, Toast.LENGTH_LONG).show();
                            Device equipos = snapshot.getValue(Device.class);
                            listDevice.add(equipos);
                            adapter = new ArrayAdapter<Device>(DeleteDevice.this,android.R.layout.simple_list_item_1,listDevice);
                            ListDev.setAdapter(adapter);
                            txtIdDev.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(txtIdDev.getText())) {
                    Device device = new Device();
                    device.setId(equipoSeleccionado.getId());
                    firebase.child(getString(R.string.nodo_Equipos)).child(device.getId()).removeValue();
                    Toast.makeText(DeleteDevice.this, "Equipo Eliminado", Toast.LENGTH_LONG).show();
                    txtIdDev.setText("");
                    listDevice.clear();
                }
                else {
                    Toast.makeText(DeleteDevice.this,"Campo Vacio" + "\n" + "Seleccione el Equipo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

