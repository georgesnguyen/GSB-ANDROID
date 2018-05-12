package rv.gsb.fr.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.gsb.rv.adaptateur.RapportVisiteAdaptateur;
import fr.gsb.rv.adpater.RapportVisiteAdapter;
import fr.gsb.rv.entites.DateFr;
import fr.gsb.rv.modeles.ModeleGsb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.gsb.rv.entites.RapportVisite;
import fr.gsb.rv.technique.Session;
import rv.gsb.fr.myapplication.R;

public class listeRapportVisite extends AppCompatActivity {

    //A REMPLACER PAR DATA SERVER !
    private List<RapportVisite> listRapport = new ArrayList<RapportVisite>();
    ListView listView ;
    TextView tvList ;
    TextView tvRapportSelec;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_rapports);
       // ModeleGsb modeleGsb = ModeleGsb.getInstance();
        Bundle paquet = this.getIntent().getExtras();
        String mois = paquet.getString("mois");
        String annee = paquet.getString("annee");


        int newAnnee = Integer.parseInt(annee);
        int NumMois;
        switch (mois) {
            case "Janvier":
                NumMois = 1;
                break;
            case "Fevrier":
                NumMois = 2;
                break;
            case "Mars":
                NumMois = 3;
                break;
            case "Avril":
                NumMois = 4;
                break;
            case "Mai":
                NumMois = 5;
                break;
            case "Juin":
                NumMois = 6;
                break;
            case "Juillet":
                NumMois = 7;
                break;
            case "Aout":
                NumMois = 8;
                break;
            case "Septembre":
                NumMois = 9;
                break;
            case "Octobre":
                NumMois = 10;
                break;
            case "Novembre":
                NumMois = 11;
                break;
            case "Decembre":
                NumMois = 12;
                break;
            default:
                NumMois = 1;
        }

        try {

            final String rap = String.valueOf(NumMois) +'.'+ String.valueOf(newAnnee)+'.'+Session.getSession().getLeVisiteur().getMatricule();
            String visivisi = URLEncoder.encode(String.valueOf(rap), "UTF-8");
            final String url = String.format("http://192.168.130.147:5000/rapport/%s", visivisi);

            Response.Listener<JSONArray> ecouteur = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(final JSONArray response) {


                    for (int i = 0; i < response.length(); i++){

                        try {

                            JSONObject jsonObject = response.getJSONObject(i);

                            /*RapportVisite(int numero, String bilan, int coefConfiance, DateFr dateVisite, DateFr dateRedaction,boolean lu)*/

                            String[] dateVisite = jsonObject.getString("dateVisite").split("-");
                            String[] dateRedac = jsonObject.getString("rapDate").split("-");

                            int anneeVisite, moisVisite, jourVisite, anneeRedac, moisRedac, jourRedac ;

                            anneeVisite = Integer.parseInt(dateVisite[0]);
                            moisVisite = Integer.parseInt(dateVisite[1]);
                            jourVisite = Integer.parseInt(dateVisite[2]);

                            anneeRedac = Integer.parseInt(dateRedac[0]);
                            moisRedac = Integer.parseInt(dateRedac[1]);
                            jourRedac = Integer.parseInt(dateRedac[2]);


                            listRapport.add(new RapportVisite(jsonObject.getInt("num_rap"), jsonObject.getString("rapBilan"), jsonObject.getString("coefLibelle"),
                                    new DateFr(jourVisite, moisVisite, anneeVisite), new DateFr(jourRedac, moisRedac, anneeRedac), false));

                            //t1.setText(lesRapportsVisites.get(i).toString());
                            Toast.makeText(listeRapportVisite.this, listRapport.get(i).toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    /*RapportVisiteAdapter adapter = new RapportVisiteAdapter(listeRapportVisite.this, listRapport);
                    listView.setAdapter(adapter);*/


                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(listeRapportVisite.this, "JE suis vide", Toast.LENGTH_LONG).show();
                }
            };
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,ecouteur, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(listeRapportVisite.this);
            requestQueue.add(jsonArrayRequest);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}



