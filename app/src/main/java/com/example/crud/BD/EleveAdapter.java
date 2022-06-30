package com.example.crud.BD;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.Changer;
import com.example.crud.Information;
import com.example.crud.InsererEleve;
import com.example.crud.ListeEleve;
import com.example.crud.R;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.MyViewHolder> {
    private Context context;
    private int position;
    ArrayList<EleveModel> eleveModelsArralist;
    public static String id_row;

    public EleveAdapter(Context context, ArrayList<EleveModel> eleveModelsArralist) {
        this.context = context;
        this.eleveModelsArralist = new ArrayList<>(eleveModelsArralist);
    }

    @NonNull
    @Override
    public EleveAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ligne_eleve, parent, false);
        return new EleveAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EleveAdapter.MyViewHolder holder, int position) {
        final EleveModel eleveModel = eleveModelsArralist.get(position);
        ConnexionBDD bdd = new ConnexionBDD(context);
        String nom_reduit = filterName(eleveModel.getNom().toUpperCase());
        String prenom_reduit = filterName(eleveModel.getPrenom().toUpperCase());
        holder.nom.setText(nom_reduit);
        holder.prenom.setText(prenom_reduit);
        holder.create_at.setText(String.valueOf(eleveModel.getCreate_at().toLowerCase()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String identifiant = eleveModel.getId().toString();
                bdd.supprimerEleve(identifiant);
                Intent j = new Intent(context, InsererEleve.class);
                context.startActivity(j);
            }
        });
        holder.afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(context, Information.class);
                h.putExtra("nom",eleveModel.getNom());
                h.putExtra("prenom",eleveModel.getPrenom());
                h.putExtra("date",eleveModel.getCreate_at());
                context.startActivity(h);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(context, Changer.class);
                h.putExtra("nom",eleveModel.getNom());
                id_row = eleveModel.getId().toString();
                h.putExtra("id_row",id_row);
                h.putExtra("prenom",eleveModel.getPrenom());
                context.startActivity(h);

            }
        });

    }

    @Override
    public int getItemCount() {
        return eleveModelsArralist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prenom,nom,create_at;
        ImageView delete, edit;
        LinearLayout afficher;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prenom = itemView.findViewById(R.id.prenom);
            nom = itemView.findViewById(R.id.nom);
            create_at = itemView.findViewById(R.id.create_at);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
            afficher = itemView.findViewById(R.id.afficher);
        }
    }

    //fonction qui diminue les valeurs a afficher

    public String filterName(String chaine){
        String str = null;
        if (chaine.length()>=5){
            str = chaine.substring(0,5)+"...";
        }
        return  str;
    }
}
