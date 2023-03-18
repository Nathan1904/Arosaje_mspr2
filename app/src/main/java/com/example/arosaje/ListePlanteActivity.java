package com.example.arosaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arosaje.data.AppData;
import com.example.arosaje.data.Gardiennage;
import com.example.arosaje.data.GardiennageEtat;
import com.example.arosaje.data.Plante;
import com.example.arosaje.data.User;

import java.util.ArrayList;
import java.util.List;

public class ListePlanteActivity extends AppCompatActivity {

    private Button btnAccepter;
    private TextView tvNomPlante;
    private ListView lvPlante;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_plante);

        btnAccepter = (Button) findViewById(R.id.btnAccepter);
        tvNomPlante = (TextView) findViewById(R.id.edNomPlante);
        lvPlante = (ListView) findViewById(R.id.lvPlante);
        btnAccepter.setOnClickListener(btnaccepterListener);
        AppData appData = AppData.getInstance();

        Intent intent = getIntent();
        Integer userId = intent.getIntExtra("UserId", 0);
        user = appData.listUsers.get(userId);

        tvNomPlante.setText(user.getNomUtilisateur());
        //Remplissage de la liste
        List<String> planteListe = new ArrayList<>();
        List<Plante>listePlantes = appData.listPlantes;
        ArrayAdapter<Plante> arrayAdapter = new ArrayAdapter<Plante>(this, android.R.layout.simple_selectable_list_item, listePlantes);
        lvPlante.setAdapter(arrayAdapter);
        lvPlante.setOnItemClickListener(lvPlanteListener);

    }

    private View.OnClickListener btnaccepterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            user.setstatusGardiennage(GardiennageEtat.ENCOURS);
            AppData appData = AppData.getInstance();
            Gardiennage gardiennage = new Gardiennage(appData.nbGardiennages, appData.listUsers.get(appData.courantUserId), user);
            appData.listGardiennage.add(gardiennage);
            appData.nbGardiennages++;

            onBackPressed();
        }
    };

    private AdapterView.OnItemClickListener lvPlanteListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Object obj = lvPlante.getItemAtPosition(position);
            Plante plante = (Plante) obj;
            Intent intent = new Intent(ListePlanteActivity.this, FichePlanteActivity.class);
            intent.putExtra("mode", "visualisation");
            intent.putExtra("planteId", plante.getPlanteId().toString());
            Log.i("TAG", "OnItemClickListener: photo :"+plante.getPhoto());
            startActivity(intent);
        }
    };
}