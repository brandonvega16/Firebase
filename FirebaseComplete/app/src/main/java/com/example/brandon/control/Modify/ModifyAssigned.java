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

import com.example.brandon.control.Model.Assigned;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyAssigned extends AppCompatActivity {

    EditText txtIdAsig,txtEq,txtEmpleado,txtIp;
    Spinner spDepa,spEdif;
    Button btnModify, btnQuery;
    ListView ListAssig;
    ArrayAdapter<CharSequence> depa,edif;
    private List<Assigned> listDevice = new ArrayList<Assigned>();
    ArrayAdapter adapter;
    Assigned deviceAssigned;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_assigned);

        txtIdAsig = (EditText)findViewById(R.id.IDASSIG);
        txtEq = (EditText)findViewById(R.id.IdEq);
        txtEmpleado = (EditText)findViewById(R.id.NomEmp);
        txtIp = (EditText)findViewById(R.id.DirecIP);

        spDepa =(Spinner)findViewById(R.id.SPDep);
        spEdif = (Spinner)findViewById(R.id.SPEdi);

        ListAssig = (ListView)findViewById(R.id.ListAsigned);

        btnModify = (Button)findViewById(R.id.ModAsig);
        btnQuery = (Button) findViewById(R.id.ConsulAsigned);

        depa = ArrayAdapter.createFromResource(this, R.array.Departamento, android.R.layout.simple_spinner_item);
        spDepa.setAdapter(depa);

        edif = ArrayAdapter.createFromResource(this, R.array.Edificio, android.R.layout.simple_spinner_item);
        spEdif.setAdapter(edif);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));


        ListAssig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                deviceAssigned = (Assigned) parent.getItemAtPosition(position);
                txtIdAsig.setText(deviceAssigned.getIdAsig());
                txtEq.setText(deviceAssigned.getIdEquipo());
                txtEmpleado.setText(deviceAssigned.getEmpleado());
                txtIp.setText(deviceAssigned.getDireccionIP());
            }
        });


        Bundle extra = getIntent().getExtras();
        String cod = extra.getString("IdAssig");
        txtIdAsig.setText(cod);

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Asig = txtIdAsig.getText().toString();
                Query query = firebase.child(getString(R.string.nodo_Asignados)).orderByChild("idAsig").equalTo(Asig);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont = 0;
                        listDevice.clear();
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            cont ++;
                            Toast.makeText(ModifyAssigned.this,"Registros Encontrdos: " + cont, Toast.LENGTH_LONG).show();
                            Assigned assigned = snapshot.getValue(Assigned.class);
                            listDevice.add(assigned);
                            adapter = new ArrayAdapter<Assigned>(ModifyAssigned.this,android.R.layout.simple_list_item_1,listDevice);
                            ListAssig.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mDepa = spDepa.getSelectedItem().toString();
                String mEdif = spEdif.getSelectedItem().toString();

                if (!TextUtils.isEmpty(txtIdAsig.getText().toString()) || !TextUtils.isEmpty(txtEq.getText().toString())
                        || !TextUtils.isEmpty(txtEmpleado.getText().toString()) || !TextUtils.isEmpty(txtIp.getText().toString()))
                {
                    Assigned assigned = new Assigned();
                    assigned.setIdAsig(txtIdAsig.getText().toString().trim());
                    assigned.setIdEquipo(txtEq.getText().toString().trim());
                    assigned.setEmpleado(txtEmpleado.getText().toString().trim());
                    assigned.setDepartamento(mDepa.trim());
                    assigned.setEdificio(mEdif.trim());
                    assigned.setDireccionIP(txtIp.getText().toString().trim());
                    firebase.child(getString(R.string.nodo_Asignados)).child(assigned.getIdAsig()).setValue(assigned);
                }


            }
        });


    }
}
