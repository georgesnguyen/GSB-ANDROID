package rv.gsb.fr.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import fr.gsb.rv.MenuRvActivity;
import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.modeles.ModeleGsb;
import fr.gsb.rv.technique.Session;

public class MainActivity extends AppCompatActivity {

    // Créer une variable vide
    TextView textView4;
    EditText EditMatricule;
    EditText EditMotDePasse;
    //ModeleGsb modele = ModeleGsb.getInstance();
    protected Visiteur visi ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Mettre l'id dans une variable
        textView4 = (TextView) findViewById(R.id.textView4);
        EditMatricule = (EditText) findViewById(R.id.editTextMatricule);
        EditMotDePasse = (EditText) findViewById(R.id.editTextMdp);

    }


    public void connexion(View v) throws UnsupportedEncodingException {

        String auth = EditMatricule.getText().toString().trim() +'.'+String.valueOf(EditMotDePasse.getText().toString().trim());
        final String visi = URLEncoder.encode(auth, "UTF-8");
        String url = String.format("http://192.168.130.147:5000/connexion/%s", visi);

        Response.Listener<JSONObject> ecouteur = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    Visiteur visiteur = new Visiteur();
                    visiteur.setNom(response.getString("nom"));
                    visiteur.setPrenom(response.getString("prenom"));
                    visiteur.setMdp(response.getString("mdp"));
                    visiteur.setMatricule(response.getString("matricule"));

                    Session.ouvrir(response.getString("matricule"), response.getString("mdp"), visiteur);
                    Intent i = new Intent(MainActivity.this, MenuRvActivity.class);
                    startActivity(i);

                    //Toast.makeText(MainActivity.this, response.getString("matricule")+' '+response.getString("mdp"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                /*new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Connexion refusée")
                        .setMessage("Le login et ou le mot de passe est faux." +
                                " Veuillez réessayer...")
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //Toast.makeText(MainActivity.this, "Button cliqué", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();*/
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, ecouteur,errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);


    }





}

