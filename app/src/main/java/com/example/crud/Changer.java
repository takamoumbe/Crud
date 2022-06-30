package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.BD.ConnexionBDD;
import com.example.crud.BD.EleveAdapter;
import com.example.crud.BD.EleveModel;

public class Changer extends AppCompatActivity {
    private Button valider;
public TextView nom, prenom;
EleveModel eleveModel;
ConnexionBDD bdd = new ConnexionBDD(Changer.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer);

        valider = findViewById(R.id.valider);
        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        String nom_ = getIntent().getStringExtra("nom");
        String prenom_ = getIntent().getStringExtra("prenom");
        String id_val = getIntent().getStringExtra("id_row");
        nom.setText(nom_);
        prenom.setText(prenom_);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom_value = nom.getText().toString().trim();
                String prenom_value = prenom.getText().toString().trim();
                if (nom_value.length() == 0 || prenom_value.length() == 0){
                    Toast.makeText(Changer.this, "Champs nom valides", Toast.LENGTH_SHORT).show();
                }else{
                    EleveModel eleveModel = new EleveModel(Integer.valueOf(id_val),nom_value,prenom_value,"","","",0);
                    bdd.modifierEleve(eleveModel);
                    Intent i = new Intent(Changer.this,ListeEleve.class);
                    startActivity(i);
                }
            }
        });
    }
}
