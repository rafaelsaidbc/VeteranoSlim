package rafasaid.com.br.santacruzveterano.jogadores;

/**
 * Created by Rafael on 14/08/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import rafasaid.com.br.santacruzveterano.Jogador;
import rafasaid.com.br.santacruzveterano.JogadorAdapter;
import rafasaid.com.br.santacruzveterano.R;

public class ZeGatoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_info_jogadores);

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        jogadores.add(new Jogador(R.drawable.ze_gato_corpo, "Nome: José Vanderlei",
                "Apelido: Zé Gato", null, "Posição: Lateral-esquerdo", "Número da camisa: 6",
                null));

        JogadorAdapter adapter = new JogadorAdapter(this, jogadores);

        ListView listView = (ListView) findViewById(R.id.lista_info_jogadores);

        listView.setAdapter(adapter);
    }

}