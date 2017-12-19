package rafasaid.com.br.santacruzveterano.estatisticas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rafasaid.com.br.santacruzveterano.R;

/**
 * Created by Entomologia on 02/10/2017.
 */

public class EstatisticasAdapter extends ArrayAdapter<Estatisticas> {

    public EstatisticasAdapter(Context context, ArrayList<Estatisticas> estatisticas) {
        super(context, 0, estatisticas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estatisticas estatisticas = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_estatisticas, parent, false);
        }
        TextView nomeMarcadorGol = (TextView) convertView.findViewById(R.id.nomeMarcadorGol);
        TextView golsMarcadosJogador = (TextView) convertView.findViewById(R.id.golsMarcadosJogador);

        nomeMarcadorGol.setText(estatisticas.nomeMarcador);
        golsMarcadosJogador.setText(String.valueOf(estatisticas.golsMarcador));

        return convertView;
    }
}
