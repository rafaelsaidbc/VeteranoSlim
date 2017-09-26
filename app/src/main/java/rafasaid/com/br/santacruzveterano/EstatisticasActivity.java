package rafasaid.com.br.santacruzveterano;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EstatisticasActivity extends AppCompatActivity {

    //estatísticas do time
    int vitoriasStaCruz = 0;
    int derrotasStaCruz = 0;
    int empatesStaCruz = 0;
    int golsMarcados = 0;
    int golsSofridos = 0;
    int saldoGols = 0;

    //estatísticas individuais
    int golsRomario2017 = 0;
    int golsDouglas2017 = 0;
    int golsPaulinho2017 = 0;
    int golsBoizinho2017 = 0;
    int golsFlavio2017 = 0;
    int golsPelota2017 = 0;
    int golsBaiano2017 = 0;
    int golsZeGato2017 = 0;
    int golsRafael2017 = 0;
    int golsAlisson2017 = 0;
    int golsEdmundo2017 = 0;
    int golsDu2017 = 0;
    int golsRicardo2017 = 0;
    int golsRoberto2017 = 0;
    int golsGabriel2017 = 0;
    int golsErick2017 = 0;
    int golsErli2017 = 0;
    int golsAlex2017 = 0;
    int golsBilin2017 = 0;
    int golsBruno2017 = 0;
    int golsZiel2017 = 0;
    int golsRyan2017 = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("resultado");



        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ResultadoFirebase resultadoFirebase = dataSnapshot.getValue(ResultadoFirebase.class);

                String golsStaCruzEstatisticas = resultadoFirebase.getGolsStaCruzAddResultado();
                int golsStaCruz = Integer.parseInt(golsStaCruzEstatisticas);

                String golsAdversarioEstatisticas = resultadoFirebase.getGolsAdversarioAddResultado();
                int golsAdversario = Integer.parseInt(golsAdversarioEstatisticas);


                if (golsStaCruz == golsAdversario) {
                    empatesStaCruz++;
                    TextView empates = (TextView) findViewById(R.id.empatesStaCruz);
                    empates.setText(String.valueOf(empatesStaCruz));

                } else if (golsStaCruz > golsAdversario) {
                    vitoriasStaCruz++;
                    TextView vitorias = (TextView) findViewById(R.id.vitoriasStaCruz);
                    vitorias.setText(String.valueOf(vitoriasStaCruz));

                } else {
                    derrotasStaCruz++;
                    TextView derrotas = (TextView) findViewById(R.id.derrotasStaCruz);
                    derrotas.setText(String.valueOf(derrotasStaCruz));
                }

                golsMarcados = golsMarcados + golsStaCruz;
                TextView golsMarcadosStaCruz = (TextView) findViewById(R.id.golsMarcadosStaCruz);
                golsMarcadosStaCruz.setText(String.valueOf(golsMarcados));

                golsSofridos = golsSofridos + golsAdversario;
                TextView golsSofridosStaCruz = (TextView) findViewById(R.id.golsSofridosStaCruz);
                golsSofridosStaCruz.setText(String.valueOf(golsSofridos));

                saldoGols = golsMarcados - golsSofridos;
                TextView saldoGolsStaCruz = (TextView) findViewById(R.id.saldoGolsStaCruz);
                saldoGolsStaCruz.setText(String.valueOf(saldoGols));

                //cria a String golsMarcadores2017 como objeto resultadoFirebase.getGolsMarcadoresAddResultado();
                //que pega a lista de marcadores do gols na database resultado da Firebasedatabase
                String golsMarcadores2017 = resultadoFirebase.getGolsMarcadoresAddResultado();

                //cria uma lista de String com os nomes dos marcadores dos gols e divide (split), para
                //cada nome individualmente existente na lista, usando , e espaço como separadores
                List<String> separadorGolsMarcadores = new ArrayList<String>(Arrays.asList(golsMarcadores2017.toString().split(", ")));

                //pega o separadorGolsMarcadores e vê se [contains] a String informada entre ""
                if (separadorGolsMarcadores.contains("Romario")) {
                    golsRomario2017++; //retorna e aumenta a contagem se o retorno do separadorGolsMarcadores for verdadeiro
                    TextView golsRomario2017TextView = (TextView) findViewById(R.id.golsRomario2017);//cria uma golsRomario2017TextView identificando-a pelo R.id.
                    golsRomario2017TextView.setText(String.valueOf(golsRomario2017));//retorna o valor colocando um valor inteiro(String.valueOf) na textview informada
                }
                if (separadorGolsMarcadores.contains("Douglas")) {
                    golsDouglas2017++;
                    TextView golsDouglas2017TextView = (TextView) findViewById(R.id.golsDouglas2017);
                    golsDouglas2017TextView.setText(String.valueOf(golsDouglas2017));
                }
                if (separadorGolsMarcadores.contains("Paulinho")) {
                    golsPaulinho2017++;
                    TextView golsPaulinho2017TextView = (TextView) findViewById(R.id.golsPaulinho2017);
                    golsPaulinho2017TextView.setText(String.valueOf(golsPaulinho2017));
                }
                if (separadorGolsMarcadores.contains("Boizinho")) {
                    golsBoizinho2017++;
                    TextView golsBoizinho2017TextView = (TextView) findViewById(R.id.golsBoizinho2017);
                    golsBoizinho2017TextView.setText(String.valueOf(golsBoizinho2017));
                }
                if (separadorGolsMarcadores.contains("Flávio")) {
                    golsFlavio2017++;
                    TextView golsFlavio2017TextView = (TextView) findViewById(R.id.golsFlavio2017);
                    golsFlavio2017TextView.setText(String.valueOf(golsFlavio2017));
                }
                if (separadorGolsMarcadores.contains("Pelota")) {
                    golsPelota2017++;
                    TextView golsPelota2017TextView = (TextView) findViewById(R.id.golsPelota2017);
                    golsPelota2017TextView.setText(String.valueOf(golsPelota2017));
                }
                if (separadorGolsMarcadores.contains("Baiano")) {
                    golsBaiano2017++;
                    TextView golsBaiano2017TextView = (TextView) findViewById(R.id.golsBaiano2017);
                    golsBaiano2017TextView.setText(String.valueOf(golsBaiano2017));
                }
                if (separadorGolsMarcadores.contains("Zé Gato")) {
                    golsZeGato2017++;
                    TextView golsZeGato2017TextView = (TextView) findViewById(R.id.golsZeGato2017);
                    golsZeGato2017TextView.setText(String.valueOf(golsZeGato2017));
                }
                if (separadorGolsMarcadores.contains("Rafael")) {
                    golsRafael2017++;
                    TextView golsRafael2017TextView = (TextView) findViewById(R.id.golsRafael2017);
                    golsRafael2017TextView.setText(String.valueOf(golsRafael2017));
                }
                if (separadorGolsMarcadores.contains("Alisson")) {
                    golsAlisson2017++;
                    TextView golsAlisson2017TextView = (TextView) findViewById(R.id.golsAlisson2017);
                    golsAlisson2017TextView.setText(String.valueOf(golsAlisson2017));
                }
                if (separadorGolsMarcadores.contains("Edmundo")) {
                    golsEdmundo2017++;
                    TextView golsEdmundo2017TextView = (TextView) findViewById(R.id.golsEdmundo2017);
                    golsEdmundo2017TextView.setText(String.valueOf(golsEdmundo2017));
                }
                if (separadorGolsMarcadores.contains("Du")) {
                    golsDu2017++;
                    TextView golsDu2017TextView = (TextView) findViewById(R.id.golsDu2017);
                    golsDu2017TextView.setText(String.valueOf(golsDu2017));
                }
                if (separadorGolsMarcadores.contains("Ricardo")) {
                    golsRicardo2017++;
                    TextView golsRicardo2017TextView = (TextView) findViewById(R.id.golsRicardo2017);
                    golsRicardo2017TextView.setText(String.valueOf(golsRicardo2017));
                }
                if (separadorGolsMarcadores.contains("Roberto")) {
                    golsRoberto2017++;
                    TextView golsRoberto2017TextView = (TextView) findViewById(R.id.golsRoberto2017);
                    golsRoberto2017TextView.setText(String.valueOf(golsRoberto2017));
                }
                if (separadorGolsMarcadores.contains("Gabriel")) {
                    golsGabriel2017++;
                    TextView golsGabriel2017TextView = (TextView) findViewById(R.id.golsGabriel2017);
                    golsGabriel2017TextView.setText(String.valueOf(golsGabriel2017));
                }
                if (separadorGolsMarcadores.contains("Erick")) {
                    golsErick2017++;
                    TextView golsErick2017TextView = (TextView) findViewById(R.id.golsErick2017);
                    golsErick2017TextView.setText(String.valueOf(golsErick2017));
                }
                if (separadorGolsMarcadores.contains("Erli")) {
                    golsErli2017++;
                    TextView golsErli2017TextView = (TextView) findViewById(R.id.golsErli2017);
                    golsErli2017TextView.setText(String.valueOf(golsErli2017));
                }
                if (separadorGolsMarcadores.contains("Alex")) {
                    golsAlex2017++;
                    TextView golsAlex2017TextView = (TextView) findViewById(R.id.golsAlex2017);
                    golsAlex2017TextView.setText(String.valueOf(golsAlex2017));
                }
                if (separadorGolsMarcadores.contains("Bilin")) {
                    golsBilin2017++;
                    TextView golsBilin2017TextView = (TextView) findViewById(R.id.golsBilin2017);
                    golsBilin2017TextView.setText(String.valueOf(golsBilin2017));
                }
                if (separadorGolsMarcadores.contains("Bruno")) {
                    golsBruno2017++;
                    TextView golsBruno2017TextView = (TextView) findViewById(R.id.golsBruno2017);
                    golsBruno2017TextView.setText(String.valueOf(golsBruno2017));
                }
                if (separadorGolsMarcadores.contains("Ziel")) {
                    golsZiel2017++;
                    TextView golsZiel2017TextView = (TextView) findViewById(R.id.golsZiel2017);
                    golsZiel2017TextView.setText(String.valueOf(golsZiel2017));
                }
                if (separadorGolsMarcadores.contains("Ryan")) {
                    golsRyan2017++;
                    TextView golsRyan2017TextView = (TextView) findViewById(R.id.golsRyan2017);
                    golsRyan2017TextView.setText(String.valueOf(golsRyan2017));
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_estatisticas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acessarEstatisticas2016:
                Intent intentEstatisticas2016 = new Intent(this, Estatisticas2016Activity.class);
                this.startActivity(intentEstatisticas2016);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
