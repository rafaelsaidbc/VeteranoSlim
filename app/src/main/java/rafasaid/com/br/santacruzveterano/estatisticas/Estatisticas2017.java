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

import static rafasaid.com.br.santacruzveterano.R.id.listaMarcadoresGols2017;

public class Estatisticas2017 extends AppCompatActivity {
    //estatísticas do time
    int vitoriasStaCruz2017 = 0;
    int derrotasStaCruz2017 = 0;
    int empatesStaCruz2017 = 0;
    int golsMarcados2017 = 0;
    int golsSofridos2017 = 0;
    int saldoGols2017 = 0;

    //estatísticas individuais
    int golsAlex2017 = 0;
    int golsAlisson2017 = 0;
    int golsBaiano2017 = 0;
    int golsBilin2017 = 0;
    int golsBoizinho2017 = 0;
    int golsBruno2017 = 0;
    int golsCharles2017 = 0;
    int golsDouglas2017 = 0;
    int golsDu2017 = 0;
    int golsEdmundo2017 = 0;
    int golsErick2017 = 0;
    int golsErli2017 = 0;
    int golsFlavio2017 = 0;
    int golsGabriel2017 = 0;
    int golsHeverton2017 = 0;
    int golsPaulinho2017 = 0;
    int golsPelota2017 = 0;
    int golsRafael2017 = 0;
    int golsRicardo2017 = 0;
    int golsRoberto2017 = 0;
    int golsRomario2017 = 0;
    int golsRyan2017 = 0;
    int golsZeGato2017 = 0;
    int golsZiel2017 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatisticas2017);

        // obtém a referêcia dos resultados
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
                    empatesStaCruz2017++;
                    TextView empates = (TextView) findViewById(R.id.empatesStaCruz2017);
                    empates.setText(String.valueOf(empatesStaCruz2017));

                } else if (golsStaCruz > golsAdversario) {
                    vitoriasStaCruz2017++;
                    TextView vitorias = (TextView) findViewById(R.id.vitoriasStaCruz2017);
                    vitorias.setText(String.valueOf(vitoriasStaCruz2017));

                } else {
                    derrotasStaCruz2017++;
                    TextView derrotas = (TextView) findViewById(R.id.derrotasStaCruz2017);
                    derrotas.setText(String.valueOf(derrotasStaCruz2017));
                }

                golsMarcados2017 = golsMarcados2017 + golsStaCruz;
                TextView golsMarcadosStaCruz = (TextView) findViewById(R.id.golsMarcadosStaCruz2017);
                golsMarcadosStaCruz.setText(String.valueOf(golsMarcados2017));

                golsSofridos2017 = golsSofridos2017 + golsAdversario;
                TextView golsSofridosStaCruz = (TextView) findViewById(R.id.golsSofridosStaCruz2017);
                golsSofridosStaCruz.setText(String.valueOf(golsSofridos2017));

                saldoGols2017 = golsMarcados2017 - golsSofridos2017;
                TextView saldoGolsStaCruz = (TextView) findViewById(R.id.saldoGolsStaCruz2017);
                saldoGolsStaCruz.setText(String.valueOf(saldoGols2017));


                //cria a String golsMarcadores2017 como objeto resultadoFirebase.getGolsMarcadoresAddResultado();
                //que pega a lista de marcadores do gols na database resultado da Firebasedatabase
                String golsMarcadores2017 = resultadoFirebase.getGolsMarcadoresAddResultado();

                //cria uma lista de String (um Array) com o nome separadorGolsMarcadores, usando como base
                //golsMarcadores2017; o split(", ") faz a separação dos elementos do Array usando os
                //parâmetros passados entre aspas
                String separadorGolsMarcadores[] = golsMarcadores2017.split(", ");

                //método para retornar os número de gols marcados pelos jogadores
                if (separadorGolsMarcadores != null) {

                    //passa os parâmetros (i=0; i< item; i++) e o item (separadorGolsMarcadores.length) a serem verificados
                    //nesse caso um Array de String mas poderia ser de int, long...
                    for (int i = 0; i < separadorGolsMarcadores.length; i++) {


                        //cada caso é verificado individualmente pelos ifs; pegado o item separadorGolsMarcadores com
                        //os parâmetros[i] (i identifica e verifica cada elemento do array, .equals("String) define
                        //o parâmetro a ser encontrado em cada item do array, se for igual conta mais 1 [++]
                        if (separadorGolsMarcadores[i].equals("Romario")) {
                            golsRomario2017++; //retorna e aumenta a contagem se o retorno do separadorGolsMarcadores for verdadeiro
                        }

                        if (separadorGolsMarcadores[i].equals("Alex")) {
                            golsAlex2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Alisson")) {
                            golsAlisson2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Baiano")) {
                            golsBaiano2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Bilin")) {
                            golsBilin2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Boizinho")) {
                            golsBoizinho2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Bruno")) {
                            golsBruno2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Charles")) {
                            golsCharles2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Douglas")) {
                            golsDouglas2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Du")) {
                            golsDu2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Edmundo")) {
                            golsEdmundo2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erick")) {
                            golsErick2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Erli")) {
                            golsErli2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Flavio")) {
                            golsFlavio2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Gabriel")) {
                            golsGabriel2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Heverton")) {
                            golsHeverton2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Paulinho")) {
                            golsPaulinho2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Pelota")) {
                            golsPelota2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Rafael")) {
                            golsRafael2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ricardo")) {
                            golsRicardo2017++;
                        }
                        if (separadorGolsMarcadores[i].equals("Roberto")) {
                            golsRoberto2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ryan")) {
                            golsRyan2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ze Gato")) {
                            golsZeGato2017++;
                        }

                        if (separadorGolsMarcadores[i].equals("Ziel")) {
                            golsZiel2017++;
                        }

                        //cria um ArrayList chamado arrayMarcadoresGols tendo como parâmetros os dados definidos na classe
                        //Estatisticas.java
                        ArrayList<Estatisticas> arrayMarcadoresGols = new ArrayList<Estatisticas>();

                        //cria um EstatísticasAdapter chamado adapterMarcadoresGols com o contexto
                        //definido na class EstatisticasAdapter.java, definindo naquela classe como
                        //os dados serão exibidos
                        EstatisticasAdapter adapterMarcadoresGols = new EstatisticasAdapter(getApplicationContext(), arrayMarcadoresGols);

                        //cria a ListView golsOsMarcadores2017 e informa onde a lista será exibida
                        final ListView golsOsMarcadores2017 = (ListView) findViewById(listaMarcadoresGols2017);


                        //pega a ListView golsOsMarcadores2017 criada acima e coloca-a no formato do
                        //adapter criado adapterMarcadoresGols
                        golsOsMarcadores2017.setAdapter(adapterMarcadoresGols);

                        //cria um objeto romarioGols2017 no formato definido na classe Estatisticas.java
                        Estatisticas romarioGols2017 = new Estatisticas("Romario", golsRomario2017);
                        //adiciona os dados informados acima no adapterMarcadoresGols
                        adapterMarcadoresGols.add(romarioGols2017);

                        Estatisticas alexGols2017 = new Estatisticas("Alex", golsAlex2017);
                        adapterMarcadoresGols.add(alexGols2017);

                        Estatisticas alissonGols2017 = new Estatisticas("Alisson", golsAlisson2017);
                        adapterMarcadoresGols.add(alissonGols2017);

                        Estatisticas baianoGols2017 = new Estatisticas("Baiano", golsBaiano2017);
                        adapterMarcadoresGols.add(baianoGols2017);

                        Estatisticas bilinGols2017 = new Estatisticas("Bilin", golsBilin2017);
                        adapterMarcadoresGols.add(bilinGols2017);

                        Estatisticas boizinhooGols2017 = new Estatisticas("Boizinho", golsBoizinho2017);
                        adapterMarcadoresGols.add(boizinhooGols2017);

                        Estatisticas brunoGols2017 = new Estatisticas("Bruno", golsBruno2017);
                        adapterMarcadoresGols.add(brunoGols2017);

                        Estatisticas charlesGols2017 = new Estatisticas("Charles", golsCharles2017);
                        adapterMarcadoresGols.add(charlesGols2017);

                        Estatisticas douglasGols2017 = new Estatisticas("Douglas", golsDouglas2017);
                        adapterMarcadoresGols.add(douglasGols2017);

                        Estatisticas duGols2017 = new Estatisticas("Du", golsDu2017);
                        adapterMarcadoresGols.add(duGols2017);

                        Estatisticas edmundoGols2017 = new Estatisticas("Edmundo", golsEdmundo2017);
                        adapterMarcadoresGols.add(edmundoGols2017);

                        Estatisticas erickGols2017 = new Estatisticas("Erick", golsErick2017);
                        adapterMarcadoresGols.add(erickGols2017);

                        Estatisticas erliGols2017 = new Estatisticas("Erli", golsErli2017);
                        adapterMarcadoresGols.add(erliGols2017);

                        Estatisticas flavioGols2017 = new Estatisticas("Flávio", golsFlavio2017);
                        adapterMarcadoresGols.add(flavioGols2017);

                        Estatisticas gabrielGols2017 = new Estatisticas("Gabriel", golsGabriel2017);
                        adapterMarcadoresGols.add(gabrielGols2017);

                        Estatisticas hevertonGols2017 = new Estatisticas("Heverton", golsHeverton2017);
                        adapterMarcadoresGols.add(hevertonGols2017);

                        Estatisticas paulinhoGols2017 = new Estatisticas("Paulinho", golsPaulinho2017);
                        adapterMarcadoresGols.add(paulinhoGols2017);

                        Estatisticas pelotaGols2017 = new Estatisticas("Pelota", golsPelota2017);
                        adapterMarcadoresGols.add(pelotaGols2017);

                        Estatisticas rafaelGols2017 = new Estatisticas("Rafael", golsRafael2017);
                        adapterMarcadoresGols.add(rafaelGols2017);

                        Estatisticas ricardoGols2017 = new Estatisticas("Ricardo", golsRicardo2017);
                        adapterMarcadoresGols.add(ricardoGols2017);

                        Estatisticas robertoGols2017 = new Estatisticas("Roberto", golsRoberto2017);
                        adapterMarcadoresGols.add(robertoGols2017);

                        Estatisticas ryanGols2017 = new Estatisticas("Ryan", golsRyan2017);
                        adapterMarcadoresGols.add(ryanGols2017);

                        Estatisticas zeGatoGols2017 = new Estatisticas("Zé Gato", golsZeGato2017);
                        adapterMarcadoresGols.add(zeGatoGols2017);

                        Estatisticas zielGols2017 = new Estatisticas("Ziel", golsZiel2017);
                        adapterMarcadoresGols.add(zielGols2017);

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
}
