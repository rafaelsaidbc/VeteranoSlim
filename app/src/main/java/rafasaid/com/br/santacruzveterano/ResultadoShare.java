package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoShare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_share);

        //recupera os dados que foram armazenados na activity ResultadoAdapter, usa-se final para
        //dar certo
        final Intent intent = getIntent();

        //cria uma String com nome dataResultado, intent recupera os dados armazenados em outra
        // activity utilizando getStringExtra [nome com o qual a variável foi armazenada na outra
        //activity, neste caso dataResultadoIntent]
        final String dataResultado = intent.getStringExtra("dataResultadoIntent");
        //cria um TextView dataResultadoShareTextView que irá mostrar os dados recuperados da outra
        //activity, buscando pelo findViewById o local onde será mostrada, neste caso data_resultado_share
        TextView dataResultadoShareTextView = (TextView) findViewById(R.id.data_resultado_share);
        //faz o dataResultadoShareTextView mostrar o texto armazenado pela variável dataResultado
        dataResultadoShareTextView.setText(dataResultado);

        final String golsStaCruzResultado = intent.getStringExtra("golsStaCruzResultadoIntent");
        TextView golsStaCruzResultadoShareTextView = (TextView) findViewById(R.id.gols_sta_cruz_resultado_share);
        golsStaCruzResultadoShareTextView.setText(golsStaCruzResultado);

        final String golsAdversarioResultado = intent.getStringExtra("golsAdversarioResultadoIntent");
        TextView golsAdversarioResultadoShareTextView = (TextView) findViewById(R.id.gols_adversario_resultado_share);
        golsAdversarioResultadoShareTextView.setText(golsAdversarioResultado);

        final String adversarioResultado = intent.getStringExtra("adversarioResultadoIntent");
        TextView adversarioResultadoShareTextView = (TextView) findViewById(R.id.adversario_resultado_share);
        adversarioResultadoShareTextView.setText(adversarioResultado);

        final String golsMarcadoresResultado = intent.getStringExtra("golsMarcadoresResultadoIntent");
        TextView golsMarcadoresResultadoShareTextView = (TextView) findViewById(R.id.gols_marcadores_resultado_share);
        golsMarcadoresResultadoShareTextView.setText(golsMarcadoresResultado);

        //faz o Button btnShareResultado iniciar
        Button btnShareIntent = (Button) findViewById(R.id.btnShareResultado);
        btnShareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ao clicar o Button cria uma nova Intent, que permitirá compartilhar os dados pela
                //ação Intent.ACTION_SEND
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                //cria a String dadosResultado com as informações que serão passadas, pode-se colocar
                //várias informações de texto desde que se use o + [mais], o "\n" faz uma quebra de
                //parágrafo nas informações que serão repassadas
                String dadosResultado = "O resultado do dia " + dataResultado + " foi:" + "\n" + "Santa Cruz " +
                        golsStaCruzResultado + " x " + golsAdversarioResultado + " " + adversarioResultado +
                        "\n" + "Os gols foram marcados por: " + "\n" + golsMarcadoresResultado;
                //determina o tipo de informação que será passada, nesse caso text
                shareIntent.setType("text/plain");
                //coloca as informações da String dataResultado na Intent shareIntent
                shareIntent.putExtra(Intent.EXTRA_TEXT, dadosResultado);
                //inicia a atividade dando um menu de opções de compartilhamento, os apps compatíveis
                //serão definidos pelo que está instalado no dispositivo que está realizando o compartilhamento
                //das informações
                startActivity(Intent.createChooser(shareIntent, "Compartilhar com:"));
            }
        });

    }
}
