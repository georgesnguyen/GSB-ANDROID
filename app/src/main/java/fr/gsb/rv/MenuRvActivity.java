package fr.gsb.rv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.gsb.rv.technique.Session;
import rv.gsb.fr.myapplication.R;
import rv.gsb.fr.myapplication.consulterRapportVisite;
import rv.gsb.fr.myapplication.saisirRapportVisite;

import android.content.Intent;

import android.view.View;


public class MenuRvActivity extends AppCompatActivity {

    TextView nom, prenom ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rv);

        nom = (TextView) findViewById(R.id.textView);
        prenom = (TextView) findViewById(R.id.textView5);


        String visiNom = Session.getSession().getLeVisiteur().getNom();

        nom.setText(visiNom );
        prenom.setText((CharSequence) Session.getSession().getLeVisiteur().getPrenom());


    }



    public void consulterRV(View vue){

            Intent intentionEnvoyer = new Intent(this , consulterRapportVisite.class);
            startActivity(intentionEnvoyer);

    }

    public void saisirRV (View vue){

        Intent intentionEnvoyer = new Intent(this , saisirRapportVisite.class);
        startActivity(intentionEnvoyer);

    }

}

