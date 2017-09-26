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

public class PaulinhoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_info_jogadores);

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        jogadores.add(new Jogador(R.drawable.paulinho_corpo, "Nome: Paulo Arcanjo",
                "Apelido: Paulinho", "Data de Nascimento: XX/XX/XXXX", "Posição: Zagueiro", "Número da camisa: XX",
                "Ano de ingresso: XXXX"));

        JogadorAdapter adapter = new JogadorAdapter(this, jogadores);

        ListView listView = (ListView) findViewById(R.id.lista_info_jogadores);

        listView.setAdapter(adapter);
    }

}