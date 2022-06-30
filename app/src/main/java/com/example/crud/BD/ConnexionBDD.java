package com.example.crud.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConnexionBDD extends SQLiteOpenHelper {
    private final Context context;
    SQLiteDatabase bdd;
    private static final int VERSION_BDD        = 1;
    private static final String NOM_BD          = "crud";
    private static final String TABLE_ELEVE     = "eleve";
    private static final String ID_ELEVE        = "_id";
    private static final String NOM_ELEVE       = "nom";
    private static final String PRENOM_ELEVE    = "prenom";
    private static final String DELETE_AT       = "delete_at";
    private static final String UPDATE_AT       = "update_at";
    private static final String CREATE_AT       = "create_at";
    private static final String ETAT_ELEVE      = "etat";


    // requete a executer pour creer une table
    private static final String REQUETE = "CREATE TABLE "+TABLE_ELEVE+" ( "+
            ID_ELEVE +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NOM_ELEVE+" TEXT, "+
            PRENOM_ELEVE+" TEXT, "+
            DELETE_AT+" TEXT, "+
            UPDATE_AT+" TEXT, "+
            CREATE_AT+" TEXT, "+
            ETAT_ELEVE+" INTEGER "+ " );";

    // fonction qui crée et etablir la connexion avec notre base de donnee
    public ConnexionBDD(Context context) {
        super(context, NOM_BD, null, VERSION_BDD);
        this.context = context;
    }

    // cette fonction permet de creer notre table eleve ont peut egalement creer une table et inserer les valeurs en ecrivant une simple
    //requete et en l executant dans cette fonction
    @Override
    public void onCreate(SQLiteDatabase bdd) {
        bdd.execSQL(REQUETE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        bdd.execSQL("DROP TABLE IF EXISTS " + TABLE_ELEVE);
        onCreate(bdd);
    }
    @Override
    public void close(){
        bdd.close();
    }

    // fonction qui insere l'eleve ont construit l'object eleve et ont passe en parametre de cette fonction
    public void insererEleve(EleveModel eleve){
        SQLiteDatabase bdd = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOM_ELEVE,eleve.getNom().toLowerCase());
        cv.put(PRENOM_ELEVE,eleve.getPrenom().toLowerCase());
        cv.put(DELETE_AT,eleve.getDelete_at());
        cv.put(UPDATE_AT,eleve.getUpdate_at());
        cv.put(CREATE_AT,eleve.getCreate_at());
        cv.put(ETAT_ELEVE,eleve.getEtat());

        long resultat = bdd.insert(TABLE_ELEVE,null,cv);
        if (resultat == -1){
            Toast.makeText(context, "Echec de l'insertion "+eleve.getNom().toLowerCase(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Insertion de "+eleve.getNom().toLowerCase()+" Réussir", Toast.LENGTH_SHORT).show();
        }
    }

    // function pour afficher les eleves
    public Cursor lireEleve(){
        String requete = "SELECT * FROM "+ TABLE_ELEVE+" WHERE "+ETAT_ELEVE+"= 0";
        SQLiteDatabase bdd = this.getReadableDatabase();
        Cursor cursor = null;
        if (bdd != null){
            cursor = bdd.rawQuery(requete, null);
        }
        return cursor;
    }

    // suprimer eleve
    public void supprimerEleve(String row_id){
        SQLiteDatabase bdd = getWritableDatabase();
        long resultat_delete = bdd.delete(TABLE_ELEVE, "_id=?", new String[]{row_id});
        if (resultat_delete == -1){
            Toast.makeText(context, "ECHEC DE SUPPRESSION", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "SUPPRESSION OK", Toast.LENGTH_SHORT).show();
        }
    }

    // cette fonction construire l'object eleve
    public EleveModel getDocumentById(String row_id){
        SQLiteDatabase bdd = this.getReadableDatabase();
        Cursor cursor = bdd.query(TABLE_ELEVE,new String[]{ID_ELEVE,NOM_ELEVE,PRENOM_ELEVE,DELETE_AT,UPDATE_AT,CREATE_AT,ETAT_ELEVE},ID_ELEVE + "=?",
                new String[]{String.valueOf(row_id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        assert  cursor != null;
        return  new EleveModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6)
        );
    }

    // fonction pour modifier un eleve

    public void modifierEleve (EleveModel eleve){
        SQLiteDatabase bdd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String row_id = eleve.getId().toString();
        cv.put(NOM_ELEVE, eleve.getNom().toLowerCase().trim());
        cv.put(PRENOM_ELEVE, eleve.getPrenom().toLowerCase().trim());

        long resultat_modif  = bdd.update(TABLE_ELEVE, cv, ID_ELEVE + "=?", new String[]{row_id});

        if (resultat_modif == -1){
            Toast.makeText(context, "ECHEC MISE A JOUR " + eleve.getNom().toUpperCase(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "MISE A JOUR " + eleve.getNom().toUpperCase() + " OK", Toast.LENGTH_SHORT).show();
        }
    }
}
