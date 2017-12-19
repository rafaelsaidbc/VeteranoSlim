package rafasaid.com.br.santacruzveterano.estatisticas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static rafasaid.com.br.santacruzveterano.R.id.listaMarcadoresGols2016;

public class Estatisticas2016 extends AppCompatActivity {

    int vitoriasStaCruz2016 = 0;
    int derrotasStaCruz2016 = 0;
    int empatesStaCruz2016 = 0;
    int golsMarcados2016 = 0;
    int golsSofridos2016 = 0;
    int saldoGols2016 = 0;

    //estatísticas individuais
    int golsRomario2016 = 0;
    int golsDouglas2016 = 0;
    int golsPaulinho2016 = 0;
    int golsDeim2016 = 0;
    int golsFlavio2016 = 0;
    int golsPelota2016 = 0;
    int golsBaiano2016 = 0;
    int golsZeGato2016 = 0;
    int golsRafael2016 = 0;
    int golsAlisson2016 = 0;
    int golsEdmundo2016 = 0;
    int golsDu2016 = 0;
    int golsRicardo2016 = 0;
    int golsRoberto2016 = 0;
    int golsGabriel2016 = 0;
    int golsErick2016 = 0;
    int golsErli2016 = 0;
    int golsAlex2016 = 0;
    int golsJoaoVictor2016 = 0;
    int golsBruno2016 = 0;
    int golsZiel2016 = 0;
    int golsPauloOtavio2016 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatisticas2016);

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("resultados2016");


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ResultadoFirebase resultadoFirebase = dataSnapshot.getValue(ResultadoFirebase.class);

                String golsStaCruzEstatisticas2016 = resultadoFirebase.getGolsStaCruzAddResultado();
                int golsStaCruz2016 = Integer.parseInt(golsStaCruzEstatisticas2016);

                String golsAdversarioEstatisticas2016 = resultadoFirebase.getGolsAdversarioAddResultado();
                int golsAdversario2016 = Integer.parseInt(golsAdversarioEstatisticas2016);


                if (golsStaCruz2016 == golsAdversario2016) {
                    empatesStaCruz2016++;
                    TextView empates2016 = (TextView) findViewById(R.id.empatesStaCruz2016);
                    empates2016.setText(String.valueOf(empatesStaCruz2016));

                } else if (golsStaCruz2016 > golsAdversario2016) {
                    vitoriasStaCruz2016++;
                    TextView vitorias2016 = (TextView) findViewById(R.id.vitoriasStaCruz2016);
                    vitorias2016.setText(String.valueOf(vitoriasStaCruz2016));

                } else {
                    derrotasStaCruz2016++;
                    TextView derrotas2016 = (TextView) findViewById(R.id.derrotasStaCruz2016);
                    derrotas2016.setText(String.valueOf(derrotasStaCruz2016));
                }

                golsMarcados2016 = golsMarcados2016 + golsStaCruz2016;
                TextView golsMarcadosStaCruz2016 = (TextView) findViewById(R.id.golsMarcadosStaCruz2016);
                golsMarcadosStaCruz2016.setText(String.valueOf(golsMarcados2016));

                golsSofridos2016 = golsSofridos2016 + golsAdversario2016;
                TextView golsSofridosStaCruz2016 = (TextView) findViewById(R.id.golsSofridosStaCruz2016);
                golsSofridosStaCruz2016.setText(String.valueOf(golsSofridos2016));

                saldoGols2016 = golsMarcados2016 - golsSofridos2016;
                TextView saldoGolsStaCruz2016 = (TextView) findViewById(R.id.saldoGolsStaCruz2016);
                saldoGolsStaCruz2016.setText(String.valueOf(saldoGols2016));

                //cria a String golsMarcadores2016 como objeto resultadoFirebase.getGolsMarcadoresAddResultado();
                //que pega a lista de marcadores do gols na database resultado da Firebasedatabase
                String golsMarcadores2016 = resultadoFirebase.getGolsMarcadoresAddResultado();

                //cria uma lista de String (um Array) com o nome separadorGolsMarcadores, usando como base
                //golsMarcadores2016; o split(", ") faz a separação dos elementos do Array usando os
                //parâmetros passados entre aspas
                String separadorGolsMarcadores[] = golsMarcadores2016.split(", ");

                //método para retornar os número de gols marcados pelos jogadores
                if (separadorGolsMarcadores != null) {

                    //passa os parâmetros (i=0; i< item; i++) e o item (separadorGolsMarcadores.length) a serem verificados
                    //nesse caso um Array de String mas poderia ser de int, long...
                    for (int i = 0; i < separadorGolsMarcadores.length; i++) {

                        //cada caso é verificado individualmente pelos ifs; pegado o item separadorGolsMarcadores com
                        //os parâmetros[i] (i identifica e verifica cada elemento do array, .equals("String) define
                        //o parâmetro a ser encontrado em cada item do array, se for igual conta mais 1 [++]
                        if (separadorGolsMarcadores[i].equals("Romario")) {
                            golsRomario2016++; //retorna e aumenta a contagem se o retorno do separadorGolsMarcadores for verdadeiro
                        }
                        if (separadorGolsMarcadores[i].equals("Douglas")) {
                            golsDouglas2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Paulinho")) {
                            golsPaulinho2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Deim")) {
                            golsDeim2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Flávio")) {
                            golsFlavio2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Pelota")) {
                            golsPelota2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Baiano")) {
                            golsBaiano2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Zé Gato")) {
                            golsZeGato2016++;
                        }

                        if (separadorGolsMarcadores[i].equals("Rafael")) {
                            golsRafael2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Alisson")) {
                            golsAlisson2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Edmundo")) {
                            golsEdmundo2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Du")) {
                            golsDu2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Ricardo")) {
                            golsRicardo2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Roberto")) {
                            golsRoberto2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Gabriel")) {
                            golsGabriel2016++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erick")) {
                            golsErick2016++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erli")) {
                            golsErli2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Alex")) {
                            golsAlex2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("João Victor")) {
                            golsJoaoVictor2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Bruno")) {
                            golsBruno2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Ziel")) {
                            golsZiel2016++;
                        }
                        if (separadorGolsMarcadores[i].equals("Paulo Otávio")) {
                            golsPauloOtavio2016++;
                        }

                        //cria um ArrayList chamado arrayMarcadoresGols tendo como parâmetros os dados definidos na classe
                        //Estatisticas.java
                        ArrayList<Estatisticas> arrayMarcadoresGols = new ArrayList<Estatisticas>();

                        //cria um EstatísticasAdapter chamado adapterMarcadoresGols com o contexto
                        //definido na class EstatisticasAdapter.java, definindo naquela classe como
                        //os dados serão exibidos
                        EstatisticasAdapter adapterMarcadoresGols = new EstatisticasAdapter(getApplicationContext(), arrayMarcadoresGols);

                        //cria a ListView golsOsMarcadores2016 e informa onde a lista será exibida
                        final ListView golsOsMarcadores2016 = (ListView) findViewById(listaMarcadoresGols2016);

                        //pega a ListView golsOsMarcadores2016 criada acima e coloca-a no formato do
                        //adapter criado adapterMarcadoresGols
                        golsOsMarcadores2016.setAdapter(adapterMarcadoresGols);

                        //cria um objeto romarioGols2016 no formato definido na classe Estatisticas.java
                        Estatisticas romarioGols2016 = new Estatisticas("Romario", golsRomario2016);
                        //adiciona os dados informados acima no adapterMarcadoresGols
                        adapterMarcadoresGols.add(romarioGols2016);

                        Estatisticas douglasGols2016 = new Estatisticas("Douglas", golsDouglas2016);
                        adapterMarcadoresGols.add(douglasGols2016);

                        Estatisticas paulinhoGols2016 = new Estatisticas("Paulinho", golsPaulinho2016);
                        adapterMarcadoresGols.add(paulinhoGols2016);

                        Estatisticas boizinhooGols2016 = new Estatisticas("João Victor", golsJoaoVictor2016);
                        adapterMarcadoresGols.add(boizinhooGols2016);

                        Estatisticas flavioGols2016 = new Estatisticas("Flávio", golsFlavio2016);
                        adapterMarcadoresGols.add(flavioGols2016);

                        Estatisticas pelotaGols2016 = new Estatisticas("Pelota", golsPelota2016);
                        adapterMarcadoresGols.add(pelotaGols2016);

                        Estatisticas baianoGols2016 = new Estatisticas("Baiano", golsBaiano2016);
                        adapterMarcadoresGols.add(baianoGols2016);

                        Estatisticas zeGatoGols2016 = new Estatisticas("Zé Gato", golsZeGato2016);
                        adapterMarcadoresGols.add(zeGatoGols2016);

                        Estatisticas rafaelGols2016 = new Estatisticas("Rafael", golsRafael2016);
                        adapterMarcadoresGols.add(rafaelGols2016);

                        Estatisticas alissonGols2016 = new Estatisticas("Alisson", golsAlisson2016);
                        adapterMarcadoresGols.add(alissonGols2016);

                        Estatisticas edmundoGols2016 = new Estatisticas("Edmundo", golsEdmundo2016);
                        adapterMarcadoresGols.add(edmundoGols2016);

                        Estatisticas duGols2016 = new Estatisticas("Du", golsDu2016);
                        adapterMarcadoresGols.add(duGols2016);

                        Estatisticas ricardoGols2016 = new Estatisticas("Ricardo", golsRicardo2016);
                        adapterMarcadoresGols.add(ricardoGols2016);

                        Estatisticas robertoGols2016 = new Estatisticas("Roberto", golsRoberto2016);
                        adapterMarcadoresGols.add(robertoGols2016);

                        Estatisticas gabrielGols2016 = new Estatisticas("Gabriel", golsGabriel2016);
                        adapterMarcadoresGols.add(gabrielGols2016);

                        Estatisticas erickGols2016 = new Estatisticas("Erick", golsErick2016);
                        adapterMarcadoresGols.add(erickGols2016);

                        Estatisticas erliGols2016 = new Estatisticas("Erli", golsErli2016);
                        adapterMarcadoresGols.add(erliGols2016);

                        Estatisticas alexGols2016 = new Estatisticas("Alex", golsAlex2016);
                        adapterMarcadoresGols.add(alexGols2016);

                        Estatisticas bilinGols2016 = new Estatisticas("Deim", golsDeim2016);
                        adapterMarcadoresGols.add(bilinGols2016);

                        Estatisticas brunoGols2016 = new Estatisticas("Bruno", golsBruno2016);
                        adapterMarcadoresGols.add(brunoGols2016);

                        Estatisticas zielGols2016 = new Estatisticas("Ziel", golsZiel2016);
                        adapterMarcadoresGols.add(zielGols2016);

                        Estatisticas ryanGols2016 = new Estatisticas("Paulo Otávio", golsPauloOtavio2016);
                        adapterMarcadoresGols.add(ryanGols2016);

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
        ;
    }
}
