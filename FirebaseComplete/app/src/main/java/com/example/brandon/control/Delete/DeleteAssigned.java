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

import com.example.brandon.control.Model.Assigned;
import com.example.brandon.control.Model.Device;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteAssigned extends AppCompatActivity {

    EditText txtIdAssig;
    Button btnConsulta;
    Button btnDelete;
    ListView ListAssigned;
    private List<Assigned> listAsigned = new ArrayList<Assigned>();
    ArrayAdapter adapter;
    Assigned assignedSelect;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_assigned);

        txtIdAssig = (EditText)findViewById(R.id.IDASSIG);
        btnConsulta = (Button)findViewById(R.id.ConAssig);
        btnDelete = (Button)findViewById(R.id.DelAssig);
        ListAssigned = (ListView)findViewById(R.id.ListAssig);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));

        ListAssigned.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                assignedSelect = (Assigned) parent.getItemAtPosition(position);
                txtIdAssig.setText(assignedSelect.getIdAsig());
            }
        });

        Bundle extra = getIntent().getExtras();
        String codigo = extra.getString("AssignedID");
        txtIdAssig.setText(codigo);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdAsig = txtIdAssig.getText().toString();
                Query query = firebase.child(getString(R.string.nodo_Asignados)).orderByChild("idAsig").equalTo(IdAsig);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            cont ++;
                            Toast.makeText(DeleteAssigned.this,"Registro Encotrado" + cont, Toast.LENGTH_LONG).show();
                            Assigned assigned = snapshot.getValue(Assigned.class);
                            listAsigned.add(assigned);
                            adapter = new ArrayAdapter<Assigned>(DeleteAssigned.this,android.R.layout.simple_list_item_1,listAsigned);
                            ListAssigned.setAdapter(adapter);
                            txtIdAssig.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtIdAssig.getText())) {
                    Assigned assigned = new Assigned();
                    assigned.setIdAsig(assignedSelect.getIdAsig());
                    firebase.child(getString(R.string.nodo_Asignados)).child(assigned.getIdAsig()).removeValue();
                    Toast.makeText(DeleteAssigned.this, "Equipo Eliminado", Toast.LENGTH_LONG).show();
                    txtIdAssig.setText("");
                    listAsigned.clear();
                }
                else {
                    Toast.makeText(DeleteAssigned.this,"Campo Vacio" + "\n" + "Seleccione el Equipo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
