package com.example.brandon.control.Query;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.brandon.control.Model.Device;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultGeneralDevice extends AppCompatActivity {

    ListView ListDevice;
    private List<Device> listDevice = new ArrayList<Device>();
    ArrayAdapter<Device> adapter;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_general_device);

        ListDevice = (ListView)findViewById(R.id.ListaGeneralDev);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));


        firebase.child(getString(R.string.nodo_Equipos)).orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listDevice.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Device device = snapshot.getValue(Device.class);
                    listDevice.add(device);
                    adapter = new ArrayAdapter<Device>(ConsultGeneralDevice.this,android.R.layout.simple_list_item_1,listDevice);
                    ListDevice.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
