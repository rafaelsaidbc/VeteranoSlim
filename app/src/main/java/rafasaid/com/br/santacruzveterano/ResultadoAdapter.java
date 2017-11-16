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
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_resultado, parent, false);
        }

        TextView idAddResultadoTextView = (TextView) convertView.findViewById(R.id.idJogoResultado);
        TextView dataAddResultadoTextView = (TextView) convertView.findViewById(R.id.data_resultado);
        TextView golsStaCruzAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado);
        TextView golsAdversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado);
        TextView adversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.adversario_resultado);
        TextView golsMarcadoresAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado);

        final ResultadoFirebase resultado = getItem(position);

        //faz com que o ImageButton seja clicável
        ImageButton mBtnShareItemResultado2017 = (ImageButton) convertView.findViewById(R.id.btnShareResultado);
        mBtnShareItemResultado2017.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //estabelece o que acontece quando o ImageButton é clicado, neste caso cria um
                //intent que abrirá uma nova class [ResultadoShare.class].
                Intent shareItemResultado2017 = new Intent(getContext(), ResultadoShare.class);
                //usa-se getContext() no lugar de this porque é um adapter, só aceitar getContext()
                //para dar certo
                getContext().startActivity(shareItemResultado2017);
                //cria um intent [shareResultado] que armazenará as variáveis para serem recuperadas
                //em outras activity, neste caso na activity ResultadoShare.class
                Intent shareResultado = new Intent(getContext(), ResultadoShare.class);
                //armazena a "dataResultadoIntent", pegando os dados de resultado.getDataAddResultado
                //buscando assim os dados da data que estão no Firebase
                shareResultado.putExtra("dataResultadoIntent", resultado.getDataAddResultado());
                shareResultado.putExtra("golsStaCruzResultadoIntent", resultado.getGolsStaCruzAddResultado());
                shareResultado.putExtra("golsAdversarioResultadoIntent", resultado.getGolsAdversarioAddResultado());
                shareResultado.putExtra("adversarioResultadoIntent", resultado.getAdversarioAddResultado());
                shareResultado.putExtra("golsMarcadoresResultadoIntent", resultado.getGolsMarcadoresAddResultado());
                //inicia a atividade com os dados armazenados em shareResultado que serão recuperados
                //em ResultadoShare.class
                getContext().startActivity(shareResultado);
            }
        });

        idAddResultadoTextView.setText(resultado.getIdAddResultado());
        dataAddResultadoTextView.setText(resultado.getDataAddResultado());
        golsStaCruzAddResultadoTextView.setText(resultado.getGolsStaCruzAddResultado());
        golsAdversarioAddResultadoTextView.setText(resultado.getGolsAdversarioAddResultado());
        adversarioAddResultadoTextView.setText(resultado.getAdversarioAddResultado());
        golsMarcadoresAddResultadoTextView.setText(resultado.getGolsMarcadoresAddResultado());

        return convertView;


    }

}


