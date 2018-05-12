package fr.gsb.rv.adpater;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.gsb.rv.entites.RapportVisite;
import rv.gsb.fr.myapplication.R;

import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;


public class RapportVisiteAdapter extends BaseAdapter {

    private Context context;
    private List<RapportVisite> lesRapportsVisite ;
    TextView num, bilan, date ;

    //Constructeur


    public RapportVisiteAdapter(Context context, List<RapportVisite> lesRapportsVisite) {
        this.context = context;
        this.lesRapportsVisite = lesRapportsVisite;
    }

    @Override
    public int getCount() {
        return lesRapportsVisite.size();
    }

    @Override
    public Object getItem(int i) {
        return lesRapportsVisite.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.item_rapport_visite, null);


        num = (TextView) v.findViewById(R.id.numRapport);
        bilan = (TextView) v.findViewById(R.id.bilan);
        date = (TextView) v.findViewById(R.id.dateVisite);

        /*String numero = String.valueOf(lesRapportsVisite.get(i).getNumero());
        num.setText(numero);*/
        //bilan.setText(lesRapportsVisite.get(i).getBilan());


        v.setTag(lesRapportsVisite.get(i).getNumero());
        return v ;
    }
}
