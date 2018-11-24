package com.example.brandon.control.Query;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brandon.control.Model.Assigned;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultAssign extends AppCompatActivity {

    EditText txtId;
    Button btnBuscar;
    ListView list;
    private List<Assigned> listDevice = new ArrayList<Assigned>();
    ArrayAdapter adapter;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_assign);

        txtId = (EditText)findViewById(R.id.IDAsig);
        list = (ListView)findViewById(R.id.ListaAsig);
        btnBuscar = (Button)findViewById(R.id.ConsultarAsig);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));

        Bundle extra = getIntent().getExtras();
        String code = extra.getString("idAssign");
        txtId.setText(code);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                Query query = firebase.child(getString(R.string.nodo_Asignados)).orderByChild("idAsig").equalTo(id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            int cont = 0;
                            listDevice.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                cont++;
                                Toast.makeText(ConsultAssign.this, "Registros Encontrdos: " + cont, Toast.LENGTH_LONG).show();
                                Assigned assigned = snapshot.getValue(Assigned.class);
                                listDevice.add(assigned);
                                adapter = new ArrayAdapter<Assigned>(ConsultAssign.this, android.R.layout.simple_list_item_1, listDevice);
                                list.setAdapter(adapter);
                            }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }
}
