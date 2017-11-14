package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ResultadoAdapter extends ArrayAdapter<ResultadoFirebase> {
    public ResultadoAdapter(Context context, int resource, List<ResultadoFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ResultadoAdapter mResultadoAdapter;
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_resultado, parent, false);
            //ToDo 3: adicionar compartilhar resultados com facebook e whatsapp
            ImageButton mBtnShareItemResultado2017 = (ImageButton) convertView.findViewById(R.id.btnShareItemResultado2017);
            mBtnShareItemResultado2017.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent shareItemResultado2017 = new Intent(Intent.ACTION_SEND);
                    shareItemResultado2017.setType("text/plain");

                    shareItemResultado2017.putExtra(Intent.EXTRA_TEXT, "");
                    getContext().startActivity(Intent.createChooser(shareItemResultado2017, "Compartilhar resultado com:"));
                }
            });
        }

        TextView idAddResultadoTextView = (TextView) convertView.findViewById(R.id.idJogoResultado);
        TextView dataAddResultadoTextView = (TextView) convertView.findViewById(R.id.data_resultado);
        TextView golsStaCruzAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado);
        TextView golsAdversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado);
        TextView adversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.adversario_resultado);
        TextView golsMarcadoresAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado);

        ResultadoFirebase resultado = getItem(position);

        idAddResultadoTextView.setText(resultado.getIdAddResultado());
        dataAddResultadoTextView.setText(resultado.getDataAddResultado());
        golsStaCruzAddResultadoTextView.setText(resultado.getGolsStaCruzAddResultado());
        golsAdversarioAddResultadoTextView.setText(resultado.getGolsAdversarioAddResultado());
        adversarioAddResultadoTextView.setText(resultado.getAdversarioAddResultado());
        golsMarcadoresAddResultadoTextView.setText(resultado.getGolsMarcadoresAddResultado());

        return convertView;
    }

}


