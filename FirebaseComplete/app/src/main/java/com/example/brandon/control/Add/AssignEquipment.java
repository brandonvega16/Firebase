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

import com.example.brandon.control.Model.Assigned;
import com.example.brandon.control.R;
import com.firebase.client.Firebase;

public class AssignEquipment extends AppCompatActivity {

    EditText txtId,txtEmpleado,txtIP;
    Spinner spDepa,spEdif;
    Button btnAssign;
    ArrayAdapter<CharSequence> depar;
    ArrayAdapter<CharSequence> edificio;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_device);

        txtId = (EditText)findViewById(R.id.IdAsig);
        txtEmpleado = (EditText)findViewById(R.id.NomEmp);
        spDepa = (Spinner) findViewById(R.id.spDepto);
        spEdif = (Spinner) findViewById(R.id.spEdificio);
        txtIP = (EditText)findViewById(R.id.DirecIP);
        btnAssign = (Button)findViewById(R.id.Asignar);

        depar = ArrayAdapter.createFromResource(this, R.array.Departamento, android.R.layout.simple_spinner_item);
        spDepa.setAdapter(depar);

        edificio = ArrayAdapter.createFromResource(this, R.array.Edificio, android.R.layout.simple_spinner_item);
        spEdif.setAdapter(edificio);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB)).child(getString(R.string.nodo_Asignados));

        Bundle extra = getIntent().getExtras();
        String code = extra.getString("idDevice");
        txtId.setText(code);

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                String empleado = txtEmpleado.getText().toString();
                String depa = spDepa.getSelectedItem().toString();
                String edif = spEdif.getSelectedItem().toString();
                String ip = txtIP.getText().toString();

                if (!TextUtils.isEmpty(id) || !TextUtils.isEmpty(empleado) || !TextUtils.isEmpty(ip))
                {
                    Assigned assigned = new Assigned();
                    String mId = firebase.push().getKey();
                    assigned.setIdAsig(mId);
                    assigned.setIdEquipo(id);
                    assigned.setEmpleado(empleado);
                    assigned.setDepartamento(depa);
                    assigned.setEdificio(edif);
                    assigned.setDireccionIP(ip);
                    firebase.child(assigned.getIdAsig()).setValue(assigned);

                    txtId.setText("");
                    txtEmpleado.setText("");
                    spDepa.setAdapter(depar);
                    spEdif.setAdapter(edificio);
                    txtIP.setText("");

                    Toast.makeText(getApplicationContext(),"Asignacion Completa",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Campos Vacios",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
