package com.example.crud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.BD.ConnexionBDD;
import com.example.crud.BD.EleveModel;

import java.time.LocalDate;

public class InsererEleve extends AppCompatActivity {
public Button valider;
public EditText prenom,nom;
ConnexionBDD bdd = new ConnexionBDD(InsererEleve.this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserer_eleve);
        valider = findViewById(R.id.valider);
        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);

    valider.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        if (prenom.getText().length() == 0 || nom.getText().length() == 0 ){
            Toast.makeText(InsererEleve.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
        }else{
            String nom_elve      = nom.getText().toString().trim();
            String prenom_eleve  = prenom.getText().toString().trim();

            String localDate = LocalDate.now().toString();
            EleveModel eleveModel = new EleveModel(null,nom_elve,prenom_eleve,"","",localDate,0);
            bdd.insererEleve(eleveModel);
            prenom.setText("");
            nom.setText("");
        }
        }
    });

    }
    // button menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mon_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.liste:
                Intent i = new Intent(InsererEleve.this,ListeEleve.class);
                startActivity(i);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
