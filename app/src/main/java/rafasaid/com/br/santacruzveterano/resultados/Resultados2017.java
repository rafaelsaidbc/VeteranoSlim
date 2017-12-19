package rafasaid.com.br.santacruzveterano.resultados;

/**
 * Created by Entomologia on 20/09/2017.
 */

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

public class Resultados2017 extends AppCompatActivity {

    private static final String TAG = "Resultados2017";

    private ListView mResultado2017ListView;
    private ResultadoAdapter2017 mResultado2017Adapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mResultadoDatabaseReference;//classe que faz referência a uma parte específica da Database;
    //para cada referência que for utilizar a database, deve ter
    //uma classe

    //leitura e exibição dos dados da database na ListView
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados2017);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database


        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso resultado,
        //pode ser calendário, resultados, fotos (no lugar de messages)
        mResultadoDatabaseReference = mFirebaseDatabase.getReference().child("resultado");

        // Inicializa as referências das Views
        mResultado2017ListView = (ListView) findViewById(R.id.resultado2017ListView);

        // Initialize message ListView and its adapter, o ArrayList é a fonte de dados do ResultadoAdapter
        //pelo objeto mResultadoAdapter
        List<ResultadoFirebase2017> resultadoFirebase2017 = new ArrayList<>();
        mResultado2017Adapter = new ResultadoAdapter2017(this, R.layout.add_item_resultado, resultadoFirebase2017);
        mResultado2017ListView.setAdapter(mResultado2017Adapter);

        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de resultado
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados do novo calendário
                ResultadoFirebase2017 resultadoFirebase2017 = dataSnapshot.getValue(ResultadoFirebase2017.class);//desserializa o resultado do banco de dados para o objeto ResultadoFirebase
                //o objeto ResultadoFirebase deve ter os mesmos campos dos objetos de resultado do banco de dados


                mResultado2017Adapter.add(resultadoFirebase2017);//adiciona o objeto ResultadoFirebase ao Adapter, converte
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
        mResultadoDatabaseReference.orderByChild("idAddResultado").addChildEventListener(mChildEventListener);


    }


}