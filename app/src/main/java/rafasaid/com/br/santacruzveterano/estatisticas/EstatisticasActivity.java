package rafasaid.com.br.santacruzveterano.estatisticas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Comparator;

import rafasaid.com.br.santacruzveterano.R;
import rafasaid.com.br.santacruzveterano.resultados.ResultadoFirebase;

import static rafasaid.com.br.santacruzveterano.R.id.listaMarcadoresGols2018;

public class EstatisticasActivity extends AppCompatActivity {

    //estatísticas do time
    int vitoriasStaCruz2018 = 0;
    int derrotasStaCruz2018 = 0;
    int empatesStaCruz2018 = 0;
    int golsMarcados2018 = 0;
    int golsSofridos2018 = 0;
    int saldoGols2018 = 0;

    //estatísticas individuais
    int golsAlex2018 = 0;
    int golsAlisson2018 = 0;
    int golsBaiano2018 = 0;
    int golsBoizinho2018 = 0;
    int golsBruno2018 = 0;
    int golsCharles2018 = 0;
    int golsDouglas2018 = 0;
    int golsDu2018 = 0;
    int golsEdmundo2018 = 0;
    int golsErick2018 = 0;
    int golsErli2018 = 0;
    int golsFlavio2018 = 0;
    int golsGabriel2018 = 0;
    int golsHeverton2018 = 0;
    int golsPaulinho2018 = 0;
    int golsPelota2018 = 0;
    int golsRafael2018 = 0;
    int golsRicardo2018 = 0;
    int golsRoberto2018 = 0;
    int golsRomario2018 = 0;
    int golsRyan2018 = 0;
    int golsZeGato2018 = 0;
    int golsZiel2018 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        // obtém a referêcia dos resultados
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("resultados2018");


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ResultadoFirebase resultadoFirebase = dataSnapshot.getValue(ResultadoFirebase.class);

                String golsStaCruzEstatisticas = resultadoFirebase.getGolsStaCruzAddResultado();
                int golsStaCruz = Integer.parseInt(golsStaCruzEstatisticas);

                String golsAdversarioEstatisticas = resultadoFirebase.getGolsAdversarioAddResultado();
                int golsAdversario = Integer.parseInt(golsAdversarioEstatisticas);


                if (golsStaCruz == golsAdversario) {
                    empatesStaCruz2018++;
                    TextView empates = (TextView) findViewById(R.id.empatesStaCruz2018);
                    empates.setText(String.valueOf(empatesStaCruz2018));

                } else if (golsStaCruz > golsAdversario) {
                    vitoriasStaCruz2018++;
                    TextView vitorias = (TextView) findViewById(R.id.vitoriasStaCruz2018);
                    vitorias.setText(String.valueOf(vitoriasStaCruz2018));

                } else {
                    derrotasStaCruz2018++;
                    TextView derrotas = (TextView) findViewById(R.id.derrotasStaCruz2018);
                    derrotas.setText(String.valueOf(derrotasStaCruz2018));
                }

                golsMarcados2018 = golsMarcados2018 + golsStaCruz;
                TextView golsMarcadosStaCruz = (TextView) findViewById(R.id.golsMarcadosStaCruz2018);
                golsMarcadosStaCruz.setText(String.valueOf(golsMarcados2018));

                golsSofridos2018 = golsSofridos2018 + golsAdversario;
                TextView golsSofridosStaCruz = (TextView) findViewById(R.id.golsSofridosStaCruz2018);
                golsSofridosStaCruz.setText(String.valueOf(golsSofridos2018));

                saldoGols2018 = golsMarcados2018 - golsSofridos2018;
                TextView saldoGolsStaCruz = (TextView) findViewById(R.id.saldoGolsStaCruz2018);
                saldoGolsStaCruz.setText(String.valueOf(saldoGols2018));


                //cria a String golsMarcadores2018 como objeto resultadoFirebase.getGolsMarcadoresAddResultado();
                //que pega a lista de marcadores do gols na database resultado da Firebasedatabase
                String golsMarcadores2018 = resultadoFirebase.getGolsMarcadoresAddResultado();

                //cria uma lista de String (um Array) com o nome separadorGolsMarcadores, usando como base
                //golsMarcadores2018; o split(", ") faz a separação dos elementos do Array usando os
                //parâmetros passados entre aspas
                String separadorGolsMarcadores[] = golsMarcadores2018.split(", ");

                //método para retornar os número de gols marcados pelos jogadores
                if (separadorGolsMarcadores != null) {

                    //passa os parâmetros (i=0; i< item; i++) e o item (separadorGolsMarcadores.length) a serem verificados
                    //nesse caso um Array de String mas poderia ser de int, long...
                    for (int i = 0; i < separadorGolsMarcadores.length; i++) {


                        //cada caso é verificado individualmente pelos ifs; pegado o item separadorGolsMarcadores com
                        //os parâmetros[i] (i identifica e verifica cada elemento do array, .equals("String) define
                        //o parâmetro a ser encontrado em cada item do array, se for igual conta mais 1 [++]
                        if (separadorGolsMarcadores[i].equals("Romario")) {
                            golsRomario2018++; //retorna e aumenta a contagem se o retorno do separadorGolsMarcadores for verdadeiro
                        }

                        if (separadorGolsMarcadores[i].equals("Alex")) {
                            golsAlex2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Alisson")) {
                            golsAlisson2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Baiano")) {
                            golsBaiano2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Boizinho")) {
                            golsBoizinho2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Bruno")) {
                            golsBruno2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Charles")) {
                            golsCharles2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Douglas")) {
                            golsDouglas2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Du")) {
                            golsDu2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Edmundo")) {
                            golsEdmundo2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erick")) {
                            golsErick2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erli")) {
                            golsErli2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Flavio")) {
                            golsFlavio2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Gabriel")) {
                            golsGabriel2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Heverton")) {
                            golsHeverton2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Paulinho")) {
                            golsPaulinho2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Pelota")) {
                            golsPelota2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Rafael")) {
                            golsRafael2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ricardo")) {
                            golsRicardo2018++;
                        }
                        if (separadorGolsMarcadores[i].equals("Roberto")) {
                            golsRoberto2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ryan")) {
                            golsRyan2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ze Gato")) {
                            golsZeGato2018++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ziel")) {
                            golsZiel2018++;
                        }

                        //cria um ArrayList chamado arrayMarcadoresGols tendo como parâmetros os dados definidos na classe
                        //Estatisticas.java
                        ArrayList<Estatisticas> arrayMarcadoresGols = new ArrayList<Estatisticas>();

                        //cria um EstatísticasAdapter chamado adapterMarcadoresGols com o contexto
                        //definido na class EstatisticasAdapter.java, definindo naquela classe como
                        //os dados serão exibidos
                        EstatisticasAdapter adapterMarcadoresGols = new EstatisticasAdapter(getApplicationContext(), arrayMarcadoresGols);

                        //cria a ListView golsOsMarcadores2018 e informa onde a lista será exibida
                        final ListView golsOsMarcadores2018 = (ListView) findViewById(listaMarcadoresGols2018);


                        //pega a ListView golsOsMarcadores2018 criada acima e coloca-a no formato do
                        //adapter criado adapterMarcadoresGols
                        golsOsMarcadores2018.setAdapter(adapterMarcadoresGols);

                        //cria um objeto romarioGols2018 no formato definido na classe Estatisticas.java
                        Estatisticas romarioGols2018 = new Estatisticas("Romario", golsRomario2018);
                        //adiciona os dados informados acima no adapterMarcadoresGols
                        adapterMarcadoresGols.add(romarioGols2018);

                        Estatisticas alexGols2018 = new Estatisticas("Alex", golsAlex2018);
                        adapterMarcadoresGols.add(alexGols2018);

                        Estatisticas alissonGols2018 = new Estatisticas("Alisson", golsAlisson2018);
                        adapterMarcadoresGols.add(alissonGols2018);

                        Estatisticas baianoGols2018 = new Estatisticas("Baiano", golsBaiano2018);
                        adapterMarcadoresGols.add(baianoGols2018);

                        Estatisticas boizinhooGols2018 = new Estatisticas("Boizinho", golsBoizinho2018);
                        adapterMarcadoresGols.add(boizinhooGols2018);

                        Estatisticas brunoGols2018 = new Estatisticas("Bruno", golsBruno2018);
                        adapterMarcadoresGols.add(brunoGols2018);

                        Estatisticas charlesGols2018 = new Estatisticas("Charles", golsCharles2018);
                        adapterMarcadoresGols.add(charlesGols2018);

                        Estatisticas douglasGols2018 = new Estatisticas("Douglas", golsDouglas2018);
                        adapterMarcadoresGols.add(douglasGols2018);

                        Estatisticas duGols2018 = new Estatisticas("Du", golsDu2018);
                        adapterMarcadoresGols.add(duGols2018);

                        Estatisticas edmundoGols2018 = new Estatisticas("Edmundo", golsEdmundo2018);
                        adapterMarcadoresGols.add(edmundoGols2018);

                        Estatisticas erickGols2018 = new Estatisticas("Erick", golsErick2018);
                        adapterMarcadoresGols.add(erickGols2018);

                        Estatisticas erliGols2018 = new Estatisticas("Erli", golsErli2018);
                        adapterMarcadoresGols.add(erliGols2018);

                        Estatisticas flavioGols2018 = new Estatisticas("Flávio", golsFlavio2018);
                        adapterMarcadoresGols.add(flavioGols2018);

                        Estatisticas gabrielGols2018 = new Estatisticas("Gabriel", golsGabriel2018);
                        adapterMarcadoresGols.add(gabrielGols2018);

                        Estatisticas hevertonGols2018 = new Estatisticas("Heverton", golsHeverton2018);
                        adapterMarcadoresGols.add(hevertonGols2018);

                        Estatisticas paulinhoGols2018 = new Estatisticas("Paulinho", golsPaulinho2018);
                        adapterMarcadoresGols.add(paulinhoGols2018);

                        Estatisticas pelotaGols2018 = new Estatisticas("Pelota", golsPelota2018);
                        adapterMarcadoresGols.add(pelotaGols2018);

                        Estatisticas rafaelGols2018 = new Estatisticas("Rafael", golsRafael2018);
                        adapterMarcadoresGols.add(rafaelGols2018);

                        Estatisticas ricardoGols2018 = new Estatisticas("Ricardo", golsRicardo2018);
                        adapterMarcadoresGols.add(ricardoGols2018);

                        Estatisticas robertoGols2018 = new Estatisticas("Roberto", golsRoberto2018);
                        adapterMarcadoresGols.add(robertoGols2018);

                        Estatisticas ryanGols2018 = new Estatisticas("Ryan", golsRyan2018);
                        adapterMarcadoresGols.add(ryanGols2018);

                        Estatisticas zeGatoGols2018 = new Estatisticas("Zé Gato", golsZeGato2018);
                        adapterMarcadoresGols.add(zeGatoGols2018);

                        Estatisticas zielGols2018 = new Estatisticas("Ziel", golsZiel2018);
                        adapterMarcadoresGols.add(zielGols2018);

                        //faz o adapter ordenar os dados de acordo com os parâmteros passados abaixo
                        adapterMarcadoresGols.sort(new Comparator<Estatisticas>() {
                            @Override
                            public int compare(Estatisticas gols1, Estatisticas gols2) {
                                Estatisticas g1 = (Estatisticas) gols1; //cria o comparador 1
                                Estatisticas g2 = (Estatisticas) gols2; //cria o comparador 2
                                //define como os dados serão ordenados, neste caso em ordem decrescente
                                //de gols marcados, ou seja, quem marcou mais gols aparece primeiro
                                return g1.golsMarcador > g2.golsMarcador ? -1 : (g1.golsMarcador > g2.golsMarcador ? +1 : 0);

                            }

                        });
                    }

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
                Intent intentEstatisticas2016 = new Intent(this, Estatisticas2016.class);
                this.startActivity(intentEstatisticas2016);
                break;

            case R.id.acessarEstatisticas2017:
                Intent intentEstatisticas2017 = new Intent(this, Estatisticas2017.class);
                this.startActivity(intentEstatisticas2017);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
