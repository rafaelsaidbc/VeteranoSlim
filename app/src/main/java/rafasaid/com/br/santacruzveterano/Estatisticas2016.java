package rafasaid.com.br.santacruzveterano;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                            TextView golsRomario2016TextView = (TextView) findViewById(R.id.golsRomario2016);//cria uma golsRomario2016TextView identificando-a pelo R.id.
                            golsRomario2016TextView.setText(String.valueOf(golsRomario2016));//retorna o valor colocando um valor inteiro(String.valueOf) na textview informada
                        }
                        if (separadorGolsMarcadores[i].equals("Douglas")) {
                            golsDouglas2016++;
                            TextView golsDouglas2016TextView = (TextView) findViewById(R.id.golsDouglas2016);
                            golsDouglas2016TextView.setText(String.valueOf(golsDouglas2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Paulinho")) {
                            golsPaulinho2016++;
                            TextView golsPaulinho2016TextView = (TextView) findViewById(R.id.golsPaulinho2016);
                            golsPaulinho2016TextView.setText(String.valueOf(golsPaulinho2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Deim")) {
                            golsDeim2016++;
                            TextView golsBoizinho2016TextView = (TextView) findViewById(R.id.golsDeim2016);
                            golsBoizinho2016TextView.setText(String.valueOf(golsDeim2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Flávio")) {
                            golsFlavio2016++;
                            TextView golsFlavio2016TextView = (TextView) findViewById(R.id.golsFlavio2016);
                            golsFlavio2016TextView.setText(String.valueOf(golsFlavio2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Pelota")) {
                            golsPelota2016++;
                            TextView golsPelota2016TextView = (TextView) findViewById(R.id.golsPelota2016);
                            golsPelota2016TextView.setText(String.valueOf(golsPelota2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Baiano")) {
                            golsBaiano2016++;
                            TextView golsBaiano2016TextView = (TextView) findViewById(R.id.golsBaiano2016);
                            golsBaiano2016TextView.setText(String.valueOf(golsBaiano2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Zé Gato")) {
                            golsZeGato2016++;
                            TextView golsZeGato2016TextView = (TextView) findViewById(R.id.golsZeGato2016);
                            golsZeGato2016TextView.setText(String.valueOf(golsZeGato2016));
                        }

                        if (separadorGolsMarcadores[i].equals("Rafael")) {
                            golsRafael2016++;
                            TextView golsRafael2016TextView = (TextView) findViewById(R.id.golsRafael2016);
                            golsRafael2016TextView.setText(String.valueOf(golsRafael2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Alisson")) {
                            golsAlisson2016++;
                            TextView golsAlisson2016TextView = (TextView) findViewById(R.id.golsAlisson2016);
                            golsAlisson2016TextView.setText(String.valueOf(golsAlisson2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Edmundo")) {
                            golsEdmundo2016++;
                            TextView golsEdmundo2016TextView = (TextView) findViewById(R.id.golsEdmundo2016);
                            golsEdmundo2016TextView.setText(String.valueOf(golsEdmundo2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Du")) {
                            golsDu2016++;
                            TextView golsDu2016TextView = (TextView) findViewById(R.id.golsDu2016);
                            golsDu2016TextView.setText(String.valueOf(golsDu2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Ricardo")) {
                            golsRicardo2016++;
                            TextView golsRicardo2016TextView = (TextView) findViewById(R.id.golsRicardo2016);
                            golsRicardo2016TextView.setText(String.valueOf(golsRicardo2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Roberto")) {
                            golsRoberto2016++;
                            TextView golsRoberto2016TextView = (TextView) findViewById(R.id.golsRoberto2016);
                            golsRoberto2016TextView.setText(String.valueOf(golsRoberto2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Gabriel")) {
                            golsGabriel2016++;
                            TextView golsGabriel2016TextView = (TextView) findViewById(R.id.golsGabriel2016);
                            golsGabriel2016TextView.setText(String.valueOf(golsGabriel2016));
                        }

                        if (separadorGolsMarcadores[i].equals("Erick")) {
                            golsErick2016++;
                            TextView golsErick2016TextView = (TextView) findViewById(R.id.golsErick2016);
                            golsErick2016TextView.setText(String.valueOf(golsErick2016));
                        }

                        if (separadorGolsMarcadores[i].equals("Erli")) {
                            golsErli2016++;
                            TextView golsErli2016TextView = (TextView) findViewById(R.id.golsErli2016);
                            golsErli2016TextView.setText(String.valueOf(golsErli2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Alex")) {
                            golsAlex2016++;
                            TextView golsAlex2016TextView = (TextView) findViewById(R.id.golsAlex2016);
                            golsAlex2016TextView.setText(String.valueOf(golsAlex2016));
                        }
                        if (separadorGolsMarcadores[i].equals("João Victor")) {
                            golsJoaoVictor2016++;
                            TextView golsBilin2016TextView = (TextView) findViewById(R.id.golsJoaoVictor2016);
                            golsBilin2016TextView.setText(String.valueOf(golsJoaoVictor2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Bruno")) {
                            golsBruno2016++;
                            TextView golsBruno2016TextView = (TextView) findViewById(R.id.golsBruno2016);
                            golsBruno2016TextView.setText(String.valueOf(golsBruno2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Ziel")) {
                            golsZiel2016++;
                            TextView golsZiel2016TextView = (TextView) findViewById(R.id.golsZiel2016);
                            golsZiel2016TextView.setText(String.valueOf(golsZiel2016));
                        }
                        if (separadorGolsMarcadores[i].equals("Paulo Otávio")) {
                            golsPauloOtavio2016++;
                            TextView golsRyan2016TextView = (TextView) findViewById(R.id.golsPauloOtavio2016);
                            golsRyan2016TextView.setText(String.valueOf(golsPauloOtavio2016));
                        }
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
