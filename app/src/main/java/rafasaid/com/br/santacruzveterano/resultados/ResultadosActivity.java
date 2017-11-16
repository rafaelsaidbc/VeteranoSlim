package rafasaid.com.br.santacruzveterano.resultados;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

public class ResultadosActivity extends AppCompatActivity {

    private static final String TAG = "ResultadosActivity";

    private ListView mResultadoListView;
    private ResultadoAdapter mResultadoAdapter;

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
        setContentView(R.layout.activity_resultados);


        // Find the View that shows the AdicionarResultado category
        FloatingActionButton btnAddListResultado = (FloatingActionButton) findViewById(R.id.btn_add_resultado);

        // Set a click listener on that View
        btnAddListResultado.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the jogadores category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link JogadoresActivity}
                Intent btnAddListResultadoIntent = new Intent(ResultadosActivity.this, AdicionarResultado.class);

                // Start the new activity (show jogadores activity)
                startActivity(btnAddListResultadoIntent);
            }

        });


        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database


        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso resultado,
        //pode ser calendário, resultados, fotos (no lugar de messages)
        mResultadoDatabaseReference = mFirebaseDatabase.getReference().child("resultado");

        // Inicializa as referências das Views
        mResultadoListView = (ListView) findViewById(R.id.resultadoListView);
        registerForContextMenu(mResultadoListView); //mostra opções quando segura click
        mResultadoListView.setOnCreateContextMenuListener(this);

        //Permite que os itens exibidos em resultados sejam clicáveis
        mResultadoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object listItem = mResultadoListView.getItemAtPosition(position);
               /* String shareResultado = mResultadoDatabaseReference.getRef().getKey();
                Intent shareItemResultado2017 = new Intent(Intent.ACTION_SEND);
                shareItemResultado2017.setType("text/plain");
                shareItemResultado2017.putExtra(Intent.EXTRA_TEXT, shareResultado);
                startActivity(Intent.createChooser(shareItemResultado2017, "Compartilhar resultado com:"));*/

            }
        });

        // Initialize message ListView and its adapter, o ArrayList é a fonte de dados do ResultadoAdapter
        //pelo objeto mResultadoAdapter
        List<ResultadoFirebase> resultadoFirebases = new ArrayList<>();
        mResultadoAdapter = new ResultadoAdapter(this, R.layout.add_item_resultado, resultadoFirebases);
        mResultadoListView.setAdapter(mResultadoAdapter);

        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de resultado
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados do novo calendário
                ResultadoFirebase resultadoFirebase = dataSnapshot.getValue(ResultadoFirebase.class);//desserializa o resultado do banco de dados para o objeto ResultadoFirebase
                //o objeto ResultadoFirebase deve ter os mesmos campos dos objetos de resultado do banco de dados


                mResultadoAdapter.add(resultadoFirebase);//adiciona o objeto ResultadoFirebase ao Adapter, converte
                //o resultado em um objeto ResultadoFirebase e adiciona ao Adapter, que será exibido na ListView
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //ToDo 2: adicionar método para mudar dados do resultado
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_resultados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acessarResultados2016:
                Intent intentResultados2016 = new Intent(this, Resultados2016.class);
                ;
                this.startActivity(intentResultados2016);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Atualizar");
                contextMenu.add(Menu.NONE, 2, Menu.NONE, "Deletar");

            }


    @Override
    public boolean onContextItemSelected(final MenuItem item) {
                if (item.getTitle() == "Atualizar") {
                    //ToDo stuff
                    String shareResultado = mResultadoDatabaseReference.getRef().getKey();
                    Intent shareItemResultado2017 = new Intent(Intent.ACTION_SEND);
                    shareItemResultado2017.setType("text/plain");
                    shareItemResultado2017.putExtra(Intent.EXTRA_TEXT, shareResultado);
                    startActivity(Intent.createChooser(shareItemResultado2017, "Compartilhar resultado com:"));

                } else if (item.getTitle() == "Deletar") {
                    //ToDo stuff
                }
                return true;
            }

}