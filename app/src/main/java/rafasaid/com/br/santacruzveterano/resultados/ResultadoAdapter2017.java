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

public class ResultadoAdapter2017 extends ArrayAdapter<ResultadoFirebase2017> {
    public ResultadoAdapter2017(Context context, int resource, List<ResultadoFirebase2017> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_resultado, parent, false);
        }

        TextView dataAddResultado2017TextView = (TextView) convertView.findViewById(R.id.data_resultado);
        TextView golsStaCruzAddResultado2017TextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado);
        TextView golsAdversarioAddResultado2017TextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado);
        TextView adversarioAddResultado2017TextView = (TextView) convertView.findViewById(R.id.adversario_resultado);
        TextView golsMarcadoresAddResultado2017TextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado);

        ResultadoFirebase2017 resultado2017 = getItem(position);

        dataAddResultado2017TextView.setText(resultado2017.getDataAddResultado());
        golsStaCruzAddResultado2017TextView.setText(resultado2017.getGolsStaCruzAddResultado());
        golsAdversarioAddResultado2017TextView.setText(resultado2017.getGolsAdversarioAddResultado());
        adversarioAddResultado2017TextView.setText(resultado2017.getAdversarioAddResultado());
        golsMarcadoresAddResultado2017TextView.setText(resultado2017.getGolsMarcadoresAddResultado());

        return convertView;
    }
}


