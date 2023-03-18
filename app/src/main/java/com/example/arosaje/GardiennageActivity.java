package com.example.arosaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arosaje.data.AppData;
import com.example.arosaje.data.Gardiennage;
import com.example.arosaje.data.GardiennageEtat;
import com.example.arosaje.data.User;

import java.util.ArrayList;
import java.util.List;

public class GardiennageActivity extends AppCompatActivity {

    private ListView lvAnnonce;
    private ListView lvMesGardiennage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardiennage);

        lvAnnonce = (ListView) findViewById(R.id.lvAnnonce);
        lvMesGardiennage = (ListView) findViewById(R.id.lvMesGardiennage);
        AppData appData = AppData.getInstance();

    }

    @Override
    protected void onResume () {
        super.onResume();

        AppData appData = AppData.getInstance();

        Log.i("TAG", "onCreate: "+appData.nbUsers.toString());
        //Remplissage de la liste des annonces
        List<User> annonceListe = new ArrayList<>();
        for (int i = 0; i < appData.nbUsers; i++){
            User user = appData.listUsers.get(i);
            if (user.getstatusGardiennage() == GardiennageEtat.DEMANDE){
                Log.i("TAG", "onCreate: "+user.getNomUtilisateur());
                annonceListe.add(user);
            }
        }
        ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_selectable_list_item, annonceListe);
        lvAnnonce.setAdapter(arrayAdapter);
        lvAnnonce.setOnItemClickListener(lvAnnonceListener);


        //Remplissage de la liste des gardiennage
        ArrayAdapter<Gardiennage> arrayAdapter1 = new ArrayAdapter<Gardiennage>(this, android.R.layout.simple_selectable_list_item, appData.listGardiennage);
        lvMesGardiennage.setAdapter(arrayAdapter1);
        lvMesGardiennage.setOnItemClickListener(lvMesGardiennageListener);
    }

    private AdapterView.OnItemClickListener lvAnnonceListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Object obj = lvAnnonce.getItemAtPosition(position);
            User userAnnonce = (User) obj;
            Intent intent = new Intent(GardiennageActivity.this, ListePlanteActivity.class);
            intent.putExtra("type", "vue annonce");
            intent.putExtra("UserId", userAnnonce.getUserId());
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener lvMesGardiennageListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Object obj = lvMesGardiennage.getItemAtPosition(position);
            Gardiennage gardiennage = (Gardiennage) obj;
            Intent intent = new Intent(GardiennageActivity.this, ListePlanteActivity.class);
            intent.putExtra("type", "vue gardien");
            intent.putExtra("propietaireId", gardiennage.getproprietaire().getUserId());
            startActivity(intent);
        }
    };
}