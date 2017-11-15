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

        final Intent intent = getIntent();

        final String dataResultado = intent.getStringExtra("dataResultadoIntent");
        TextView dataResultadoShareTextView = (TextView) findViewById(R.id.data_resultado_share);
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

        Button btnShareIntent = (Button) findViewById(R.id.btnShareResultado);
        btnShareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                String dadosResultado = "O resultado do dia " + dataResultado + " foi:" + "\n" + "Santa Cruz " +
                        golsStaCruzResultado + " x " + golsAdversarioResultado + " " + adversarioResultado +
                        "\n" + "Os gols foram marcados por: " + "\n" + golsMarcadoresResultado;
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, dadosResultado);
                startActivity(Intent.createChooser(shareIntent, "Compartilhar com:"));
            }
        });

    }
}
