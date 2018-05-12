package rv.gsb.fr.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;




public class consulterRapportVisite extends AppCompatActivity {

    Button btnRechercher ;
    Spinner spinnerMois, spinnerAnnee ;

    // Listes
    private static final String [] lesMois = { "Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"} ;
    private static final String [] lesAnnees = { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",} ;





     protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_rapport_visite);

        // Création des variables
        btnRechercher = (Button) findViewById(R.id.btnRechercher) ;
        spinnerMois = (Spinner) findViewById(R.id.spinnerMois) ;
        spinnerAnnee = (Spinner) findViewById(R.id.spinnerAnnee) ;

        // Design Mois
        ArrayAdapter<String> aaMois = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesMois) ;
        aaMois.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        spinnerMois.setAdapter(aaMois);

        // Design Année
        ArrayAdapter<String> aaAnnees = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesAnnees) ;
        aaAnnees.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        spinnerAnnee.setAdapter(aaAnnees);

    }

    public void consulter(View vue){

        // Création du paquet
        Bundle paquet = new Bundle();
        paquet.putString("mois" , spinnerMois.getSelectedItem().toString());
        paquet.putString("annee" , spinnerAnnee.getSelectedItem().toString());

        /*Toast.makeText(consulterRapportVisite.this, String.valueOf(spinnerMois.getSelectedItem()), Toast.LENGTH_SHORT).show();
        Toast.makeText(consulterRapportVisite.this, String.valueOf(spinnerAnnee.getSelectedItem()), Toast.LENGTH_SHORT).show();*/

        Intent intentAfficher = new Intent(this, listeRapportVisite.class);
        intentAfficher.putExtras(paquet);
        startActivity(intentAfficher);

    }












    /*public void listeRapports(View vue){
        //Récupérer la valeur du mois
        String mois = Editmois.getText().toString();

        //Récupérer la valeur de l'annee
        String annee = Editannee.getText().toString();

        //Etablir une connexion
        verification = modele.afficherListeRapports(mois, annee);

        if(verification != null){
            Session.ouvrir(visiteur.getMatricule(),visiteur.getMdp() );
            Intent intentionEnvoyer = new Intent(this , listeRapportVisite.class);
            startActivity(intentionEnvoyer);
        }
        else{
            textView4.setText("La date que vous avez selectionner n'a aucune visite.");

        }

    }*/


}


