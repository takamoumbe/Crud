package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.crud.BD.ConnexionBDD;
import com.example.crud.BD.EleveAdapter;
import com.example.crud.BD.EleveModel;

import java.util.ArrayList;

public class ListeEleve extends AppCompatActivity {
public RecyclerView recyclerView;
ArrayList<EleveModel> eleveModelsArralist;
ConnexionBDD bdd;
Intent intent;
Cursor cursor;
EleveAdapter eleveAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_eleve);

        recyclerView = findViewById(R.id.recyclerView);
        bdd = new ConnexionBDD(ListeEleve.this);
        eleveModelsArralist = new ArrayList<>();
        afficherLigneEleveRecycle();
    }
    public void listEleve(){
        cursor = null;
        cursor = bdd.lireEleve();
        while (cursor.moveToNext()){
            EleveModel eleve = new EleveModel(
                    Integer.valueOf(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            );
            eleveModelsArralist.add(eleve);
            System.out.println(eleveModelsArralist);
        }
        cursor.close();
    }

    public void afficherLigneEleveRecycle(){
        listEleve();
        eleveAdapter = new EleveAdapter(ListeEleve.this,eleveModelsArralist);
        recyclerView.setAdapter(eleveAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListeEleve.this));
    }
}
