package com.example.arosaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arosaje.data.AppData;
import com.example.arosaje.data.Plante;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FichePlanteActivity extends AppCompatActivity {


    private Button btnretour;
    private Button btnSuivi;
    private Plante plante;
    private EditText edNomPlante;
    private EditText edDate;
    private EditText edLocalisation;
    private EditText edtCommentaire;
    private AppData appData;
    private String mode;
    private ImageView ivCapture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_plante);

        btnretour = (Button) findViewById(R.id.btnAccepter);
        btnSuivi = (Button) findViewById(R.id.btnSuivi);
        edDate = (EditText) findViewById(R.id.edDate);
        edLocalisation = (EditText) findViewById(R.id.edLocalisation);
        edNomPlante = (EditText) findViewById(R.id.edNomPlante);
        edtCommentaire = (EditText) findViewById(R.id.edtCommentaire);
        ivCapture = (ImageView) findViewById(R.id.ivCapture);
        ivCapture.setOnClickListener(ivCaptureListener);

        btnretour.setOnClickListener(btnretourListener);
        btnSuivi.setOnClickListener(btnSuiviListener);

        Log.i("TAG", "onCreate: fiche plante");

        appData = AppData.getInstance();
        List<Plante> plantesListe = appData.listPlantes;

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        if (mode.equals("visualisation")) {
            Integer planteId = Integer.valueOf(intent.getStringExtra("planteId"));

            plante = plantesListe.get(planteId);
            edNomPlante.setText(plante.getNomPlante());
            edDate.setText(plante.getDate());
            edLocalisation.setText(plante.getLocalisation());
            edtCommentaire.setText(plante.getCommentaire());
            File imgFile = new File(plante.getPhoto());

            // on below line we are initializing variables with ids.
            ivCapture = (ImageView) findViewById(R.id.ivCapture);

            // on below line we are checking if the image file exist or not.
            if (imgFile.exists()) {
                // on below line we are creating an image bitmap variable
                // and adding a bitmap to it from image file.
                Bitmap imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                // on below line we are setting bitmap to our image view.
                ivCapture.setImageBitmap(imgBitmap);
            } else {
                Toast.makeText(FichePlanteActivity.this, "Hoto not found!", Toast.LENGTH_SHORT).show();
                ivCapture.setImageBitmap(null);
            }
        }else {
            //mode edition
            btnSuivi.setText("Ajouter");
            btnretour.setText("Annuler");
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        AppData appData = AppData.getInstance();
        Log.i("TAG", "onResume: fiche plante");
        if (! appData.imageTemp.equals("")) {
            File imgFile = new File(appData.imageTemp);

            // on below line we are initializing variables with ids.
            ivCapture = (ImageView) findViewById(R.id.ivCapture);

            // on below line we are checking if the image file exist or not.
            if (imgFile.exists()) {
                // on below line we are creating an image bitmap variable
                // and adding a bitmap to it from image file.
                Bitmap imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                // on below line we are setting bitmap to our image view.
                ivCapture.setImageBitmap(imgBitmap);
            } else {
                Toast.makeText(FichePlanteActivity.this, "Hoto not found!", Toast.LENGTH_SHORT).show();
                ivCapture.setImageBitmap(null);
            }
        }

        // Date Heure
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        // Create DateTimeFormatter instance with specified format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // Format LocalDateTime to String
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
        edDate.setText(formattedDateTime);
    }


    private View.OnClickListener btnretourListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            appData.imageTemp = "";
            onBackPressed();
        }
    };

    private View.OnClickListener btnSuiviListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mode.equals("visualisation")){
                Intent intent = new Intent(FichePlanteActivity.this, ListePlanteActivity.class);
                startActivity(intent);
            }else {
                //mode edition
                if (! edNomPlante.getText().toString().equals("")) {
                    List<Plante> planteList = appData.listPlantes;
                    Integer nbPlantes = appData.nbPlantes;
                    Log.i("TAG", "onClick: "+appData.imageTemp);
                    Plante plante = new Plante(edNomPlante.getText().toString(), nbPlantes, nbPlantes, appData.imageTemp, edDate.getText().toString(), "Boulogne", "Nouvelle plante");
                    planteList.add(plante);
                    appData.nbPlantes++;
                    Log.i("TAG", "onClick: av  photo"+plante.getPhoto());
                    appData.imageTemp = "";
                    Log.i("TAG", "onClick:apres  photo"+plante.getPhoto());
                    onBackPressed();
                } else {
                    Toast.makeText(FichePlanteActivity.this, "Entrer un nom de plante.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    private View.OnClickListener ivCaptureListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(FichePlanteActivity.this, CameraAtivity.class);
            startActivity(intent);
        }
    };
}