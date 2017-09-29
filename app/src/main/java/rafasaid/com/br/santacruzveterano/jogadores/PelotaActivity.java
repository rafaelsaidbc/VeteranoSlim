package rafasaid.com.br.santacruzveterano.jogadores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rafasaid.com.br.santacruzveterano.R;
import rafasaid.com.br.santacruzveterano.jogadores.firebase.JogadoresFirebase;
import rafasaid.com.br.santacruzveterano.jogadores.firebase.JogadoresFirebaseAdapter;

public class PelotaActivity extends AppCompatActivity {

    private static final String TAG = "Pelota";

    private ListView mPelotaListView;
    private JogadoresFirebaseAdapter mPelotaFirebaseAdapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mPelotaDatabaseReference;//classe que faz referência a uma parte específica da Database;
    //para cada referência que for utilizar a database, deve ter
    //uma classe

    //leitura e exibição dos dados da database na ListView
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadores_firebase);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database


        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso resultado,
        //pode ser calendário, resultados, fotos (no lugar de messages)
        mPelotaDatabaseReference = mFirebaseDatabase.getReference().child("jogadores").child("laterais").child("Pelota");

        // Inicializa as referências das Views
        mPelotaListView = (ListView) findViewById(R.id.pelotaDadosListView);


        // Initialize pelotaListView and its adapter, o ArrayList é a fonte de dados do PelotaFirebaseAdapter
        //pelo objeto mPelotaFirebaseAdapter
        List<JogadoresFirebase> jogadoresFirebase = new ArrayList<>();
        mPelotaFirebaseAdapter = new JogadoresFirebaseAdapter(this, R.layout.jogadores_firebase, jogadoresFirebase);
        mPelotaListView.setAdapter(mPelotaFirebaseAdapter);


        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de resultado
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados de Pelota
                JogadoresFirebase pelotaFirebase = dataSnapshot.getValue(JogadoresFirebase.class);//desserializa o resultado do banco de dados para o objeto ResultadoFirebase
                //o objeto ResultadoFirebase deve ter os mesmos campos dos objetos de resultado do banco de dados


                mPelotaFirebaseAdapter.add(pelotaFirebase);//adiciona o objeto ResultadoFirebase ao Adapter, converte
                //o resultado em um objeto ResultadoFirebase e adiciona ao Adapter, que será exibido na ListView
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mPelotaDatabaseReference.addChildEventListener(mChildEventListener);
    }

}