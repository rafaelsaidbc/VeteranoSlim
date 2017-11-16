package rafasaid.com.br.santacruzveterano.resultados;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

/**
 * Created by Entomologia on 20/09/2017.
 */

public class ResultadoAdapter2016 extends ArrayAdapter<ResultadoFirebase2016> {
    public ResultadoAdapter2016(Context context, int resource, List<ResultadoFirebase2016> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_resultado2016, parent, false);
        }

        TextView dataAddResultado2016TextView = (TextView) convertView.findViewById(R.id.data_resultado2016);
        TextView golsStaCruzAddResultado2016TextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado2016);
        TextView golsAdversarioAddResultado2016TextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado2016);
        TextView adversarioAddResultado2016TextView = (TextView) convertView.findViewById(R.id.adversario_resultado2016);
        TextView golsMarcadoresAddResultado2016TextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado2016);

        ResultadoFirebase2016 resultado2016 = getItem(position);

        dataAddResultado2016TextView.setText(resultado2016.getDataAddResultado());
        golsStaCruzAddResultado2016TextView.setText(resultado2016.getGolsStaCruzAddResultado());
        golsAdversarioAddResultado2016TextView.setText(resultado2016.getGolsAdversarioAddResultado());
        adversarioAddResultado2016TextView.setText(resultado2016.getAdversarioAddResultado());
        golsMarcadoresAddResultado2016TextView.setText(resultado2016.getGolsMarcadoresAddResultado());

        return convertView;
    }
}


