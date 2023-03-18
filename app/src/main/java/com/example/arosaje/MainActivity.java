package com.example.arosaje;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnMesPlantes;
    private Button btnGardiennage;
    FirebaseAuth auth;
    Button btnlogout;
    FirebaseUser user;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        btnlogout = findViewById(R.id.logout);
        user = auth.getCurrentUser();
        textView = findViewById(R.id.user_details);

        btnMesPlantes = findViewById(R.id.btnMesPlantes);
        btnGardiennage = findViewById(R.id.btnGardiennage);

        btnMesPlantes.setOnClickListener(btnMesPlantesListener);
        btnGardiennage.setOnClickListener(btnGardiennageListener);
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }




    private View.OnClickListener btnMesPlantesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MesPlantesActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnGardiennageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, GardiennageActivity.class);
            startActivity(intent);

        }
    };
}