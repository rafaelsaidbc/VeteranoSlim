package rafasaid.com.br.santacruzveterano;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Estatisticas2016Activity extends AppCompatActivity {

    int vitoriasStaCruz2016 = 0;
    int derrotasStaCruz2016 = 0;
    int empatesStaCruz2016 = 0;
    int golsMarcados2016 = 0;
    int golsSofridos2016 = 0;
    int saldoGols2016 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas2016);

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
