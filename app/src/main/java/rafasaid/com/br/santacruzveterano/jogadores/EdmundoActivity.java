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

public class EdmundoActivity extends AppCompatActivity {

    private static final String TAG = "Edmundo";

    private ListView mEdmundoListView;
    private JogadoresFirebaseAdapter mEdmundoFirebaseAdapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mEdmundoDatabaseReference;//classe que faz referência a uma parte específica da Database;
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
        mEdmundoDatabaseReference = mFirebaseDatabase.getReference().child("jogadores").child("meioCampo").child("Edmundo");

        // Inicializa as referências das Views
        mEdmundoListView = (ListView) findViewById(R.id.edmundoDadosListView);


        // Initialize edmundoListView and its adapter, o ArrayList é a fonte de dados do EdmundoFirebaseAdapter
        //pelo objeto mEdmundoFirebaseAdapter
        List<JogadoresFirebase> jogadoresFirebase = new ArrayList<>();
        mEdmundoFirebaseAdapter = new JogadoresFirebaseAdapter(this, R.layout.jogadores_firebase, jogadoresFirebase);
        mEdmundoListView.setAdapter(mEdmundoFirebaseAdapter);


        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de resultado
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados de Edmundo
                JogadoresFirebase edmundoFirebase = dataSnapshot.getValue(JogadoresFirebase.class);//desserializa o resultado do banco de dados para o objeto ResultadoFirebase
                //o objeto ResultadoFirebase deve ter os mesmos campos dos objetos de resultado do banco de dados


                mEdmundoFirebaseAdapter.add(edmundoFirebase);//adiciona o objeto ResultadoFirebase ao Adapter, converte
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
        mEdmundoDatabaseReference.addChildEventListener(mChildEventListener);
    }

}