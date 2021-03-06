package rafasaid.com.br.santacruzveterano.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

public class MudarCalendario extends AppCompatActivity {

    private static final String TAG = "MudarCalendario";

    private ListView mCalendarioListView;
    private CalendarioMudarAdapter mCalendarioMudarAdapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mCalendarioDatabaseReference;//classe que faz referência a uma parte específica da Database;
    //para cada referência que for utilizar a database, deve ter
    //uma classe

    //leitura e exibição dos dados da database na ListView
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario_mudar);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database

        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso calendario,
        //pode ser calendário, resultados, fotos (no lugar de calendario)
        mCalendarioDatabaseReference = mFirebaseDatabase.getReference().child("calendario");

        // Inicializa as referências das Views
        mCalendarioListView = (ListView) findViewById(R.id.calendarioListView);
        registerForContextMenu(mCalendarioListView); //mostra opções quando segura click
        mCalendarioListView.setOnCreateContextMenuListener(this);

        //Permite que os itens exibidos em calendário sejam clicáveis
        mCalendarioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object listItem = mCalendarioListView.getItemAtPosition(position);
            }
        });


        // Initialize message ListView and its adapter, o ArrayList é a fonte de dados do CalendarioAdapter
        //pelo objeto mCalendarioAdapter
        List<CalendarioFirebase> calendarioFirebases = new ArrayList<>();
        mCalendarioMudarAdapter = new CalendarioMudarAdapter(this, R.layout.add_item_calendario, calendarioFirebases);
        mCalendarioListView.setAdapter(mCalendarioMudarAdapter);

        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de calendario
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados do novo calendário
                CalendarioFirebase calendarioFirebase = dataSnapshot.getValue(CalendarioFirebase.class);//desserializa o calendario do banco de dados para o objeto CalendarioFirebase
                //o objeto CalendarioFirebase deve ter os mesmos campos dos objetos de calendario do banco de dados

                mCalendarioMudarAdapter.add(calendarioFirebase);//adiciona o objeto CalendarioFirebase ao Adapter, converte
                //o calendario em um objeto CalendarioFirebase e adiciona ao Adapter, que será exibido na ListView
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
        mCalendarioDatabaseReference.orderByChild("idAddCalendario").addChildEventListener(mChildEventListener);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.add(Menu.NONE, 1, Menu.NONE, "Atualizar");
        contextMenu.add(Menu.NONE, 2, Menu.NONE, "Deletar");

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;

        if (item.getTitle() == "Atualizar") {
            // Cria um intent para abrir {@link AdicionarCalendario}
            Intent intentAtualizarCalendario = new Intent(MudarCalendario.this, AdicionarCalendario.class);

            // Inicia a activity AdicionarCalendario
            startActivity(intentAtualizarCalendario);

        } else if (item.getTitle() == "Deletar") {

            Query removerCalendario = mCalendarioDatabaseReference.limitToFirst(1);
            removerCalendario.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshotRemoverCalendario : dataSnapshot.getChildren()) {
                        snapshotRemoverCalendario.getRef().removeValue();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return true;
    }
}


