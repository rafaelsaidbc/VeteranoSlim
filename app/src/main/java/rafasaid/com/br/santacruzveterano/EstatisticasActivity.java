package rafasaid.com.br.santacruzveterano;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EstatisticasActivity extends AppCompatActivity {

    int vitoriasStaCruz = 0;
    int derrotasStaCruz = 0;
    int empatesStaCruz = 0;
    int golsMarcados = 0;
    int golsSofridos = 0;
    int saldoGols = 0;


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
