package fr.gsb.rv.adaptateur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.gsb.rv.entites.RapportVisite;
import rv.gsb.fr.myapplication.R;

/**
 * Created by Georges NGUYEN on 30/04/2018.
 */

public class RapportVisiteAdaptateur extends BaseAdapter {

    Context context;
    private List<RapportVisite> list;
    TextView nomPraticien, numRap, dateV;


    public RapportVisiteAdaptateur(Context context, List<RapportVisite> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v = View.inflate(context, R.layout.item_rapport_visite, null);
        //nomPraticien=(TextView) v.findViewById(R.id.nomPraticien);
        numRap=(TextView) v.findViewById(R.id.numRapport);
        dateV=(TextView) v.findViewById(R.id.dateVisite);

        nomPraticien.setText(list.get(i).getLePraticien().getNom());
        numRap.setText(list.get(i).getNumero());
        dateV.setText(list.get(i).getBilan());


        v.setTag(list.get(i).getNumero());




        return v;
    }
}
