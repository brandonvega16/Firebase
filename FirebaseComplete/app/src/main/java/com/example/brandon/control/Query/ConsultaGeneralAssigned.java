package com.example.brandon.control.Query;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.brandon.control.Model.Assigned;
import com.example.brandon.control.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultaGeneralAssigned extends AppCompatActivity {

    private List<Assigned> listAssigned = new ArrayList<Assigned>();
    ArrayAdapter<Assigned> adapter;
    ListView ListAsig;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_general_assigned);

        ListAsig = (ListView)findViewById(R.id.ListGenralAssigned);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(getString(R.string.FirebaseDB));

        //Query query = firebase.child(getString(R.string.nodo_Asignados)).orderByChild("idAsig");
        firebase.child(getString(R.string.nodo_Asignados)).orderByChild("idAsig").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAssigned.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Assigned assigned = snapshot.getValue(Assigned.class);
                    listAssigned.add(assigned);
                    adapter = new ArrayAdapter<Assigned>(ConsultaGeneralAssigned.this,android.R.layout.simple_list_item_1,listAssigned);
                    ListAsig.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
