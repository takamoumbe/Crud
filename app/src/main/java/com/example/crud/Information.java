package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.crud.BD.ConnexionBDD;
import com.example.crud.BD.EleveModel;

public class Information extends AppCompatActivity {
public TextView nom,prenom,create_at;
ConnexionBDD bdd = new ConnexionBDD(Information.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        create_at = findViewById(R.id.create_at);

        String nom_ = getIntent().getStringExtra("nom");
        String prenom_ = getIntent().getStringExtra("prenom");
        String date = getIntent().getStringExtra("date");

        nom.setText(nom_);
        prenom.setText(prenom_);
        create_at.setText(date);

    }
}
