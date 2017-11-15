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

        }

        TextView idAddResultadoTextView = (TextView) convertView.findViewById(R.id.idJogoResultado);
        TextView dataAddResultadoTextView = (TextView) convertView.findViewById(R.id.data_resultado);
        TextView golsStaCruzAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado);
        TextView golsAdversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado);
        TextView adversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.adversario_resultado);
        TextView golsMarcadoresAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado);

        final ResultadoFirebase resultado = getItem(position);

        ImageButton mBtnShareItemResultado2017 = (ImageButton) convertView.findViewById(R.id.btnShareItemResultado2017);
        mBtnShareItemResultado2017.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent shareItemResultado2017 = new Intent(getContext(), ResultadoShare.class);
                getContext().startActivity(shareItemResultado2017);


                Intent shareResultado = new Intent(getContext(), ResultadoShare.class);
                shareResultado.putExtra("dataResultadoIntent", resultado.getDataAddResultado());
                shareResultado.putExtra("golsStaCruzResultadoIntent", resultado.getGolsStaCruzAddResultado());
                shareResultado.putExtra("golsAdversarioResultadoIntent", resultado.getGolsAdversarioAddResultado());
                shareResultado.putExtra("adversarioResultadoIntent", resultado.getAdversarioAddResultado());
                shareResultado.putExtra("golsMarcadoresResultadoIntent", resultado.getGolsMarcadoresAddResultado());
                getContext().startActivity(shareResultado);
            }
        });

        idAddResultadoTextView.setText(resultado.getIdAddResultado());

        dataAddResultadoTextView.setText(resultado.getDataAddResultado());
        //Intent dataResultadoIntent = new Intent(getContext(), ResultadoShare.class);
        //dataResultadoIntent.putExtra("dataResultadoIntent", resultado.getDataAddResultado());
        //getContext().startActivity(dataResultadoIntent);

        golsStaCruzAddResultadoTextView.setText(resultado.getGolsStaCruzAddResultado());
        //Intent golsStaCruzResultadoIntent = new Intent(getContext(), ResultadoShare.class);
        //golsStaCruzResultadoIntent.putExtra("golsStaCruzResultadoIntent", resultado.getGolsStaCruzAddResultado());
        //getContext().startActivity(golsStaCruzResultadoIntent);

        golsAdversarioAddResultadoTextView.setText(resultado.getGolsAdversarioAddResultado());
        //Intent golsAdversarioResultadoIntent = new Intent(getContext(), ResultadoShare.class);
        //golsAdversarioResultadoIntent.putExtra("golsAdversarioResultadoIntent", resultado.getGolsAdversarioAddResultado());
        //getContext().startActivity(golsAdversarioResultadoIntent);

        adversarioAddResultadoTextView.setText(resultado.getAdversarioAddResultado());
        //Intent adversarioResultadoIntent = new Intent(getContext(), ResultadoShare.class);
        //adversarioResultadoIntent.putExtra("adversarioResultadoIntent", resultado.getAdversarioAddResultado());
        //getContext().startActivity(adversarioResultadoIntent);

        golsMarcadoresAddResultadoTextView.setText(resultado.getGolsMarcadoresAddResultado());
        //Intent golsMarcadoresResultadoIntent = new Intent(getContext(), ResultadoShare.class);
        //golsMarcadoresResultadoIntent.putExtra("golsMarcadoresResultadoIntent", resultado.getGolsMarcadoresAddResultado());
        //getContext().startActivity(golsMarcadoresResultadoIntent);

        return convertView;


    }

}


