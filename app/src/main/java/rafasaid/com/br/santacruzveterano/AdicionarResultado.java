package rafasaid.com.br.santacruzveterano;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Entomologia on 13/09/2017.
 */

public class AdicionarResultado extends AppCompatActivity {


    //instâncias de autenticação
    public static final int RC_SIGN_IN = 1;
    private Button mBtnAddItemResultado;
    private EditText mAnoAddResultadoEditText;
    private EditText mIdAddResultadoEditText;
    private EditText mDataAddResultadoEditText;
    private EditText mGolsStaAddResultadoEditText;
    private EditText mGolsAdversarioAddResultadoEditText;
    private EditText mAdversarioAddResultadoEditText;
    private EditText mGolsMarcadoresAddResultadoEditText;
    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    //para cada referência que for utilizar a database, deve ter
    //uma classe
    private DatabaseReference mResultadoDatabaseReference;//classe que faz referência a uma parte específica da Database;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_resultado);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database

        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso resultado,
        //pode ser calendário, resultados, fotos (no lugar de messages)
        mResultadoDatabaseReference = mFirebaseDatabase.getReference().child("resultado");

        mAnoAddResultadoEditText = (EditText) findViewById(R.id.ano_add_resultado);
        mIdAddResultadoEditText = (EditText) findViewById(R.id.id_add_resultado);
        mDataAddResultadoEditText = (EditText) findViewById(R.id.data_add_resultado);
        mGolsStaAddResultadoEditText = (EditText) findViewById(R.id.gols_sta_cruz_add_resultado);
        mGolsAdversarioAddResultadoEditText = (EditText) findViewById(R.id.gols_adversario_add_resultado);
        mAdversarioAddResultadoEditText = (EditText) findViewById(R.id.adversario_add_resultado);
        mGolsMarcadoresAddResultadoEditText = (EditText) findViewById(R.id.gols_marcadores_add_resultado);

        mBtnAddItemResultado = (Button) findViewById(R.id.btnAddItemResultado);

        // Botão adicionar envia os dados e limpa os EditText
        mBtnAddItemResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultadoFirebase resultadoFirebase = new ResultadoFirebase(
                        mAnoAddResultadoEditText.getText().toString(),
                        mIdAddResultadoEditText.getText().toString(),
                        mDataAddResultadoEditText.getText().toString(),
                        mGolsStaAddResultadoEditText.getText().toString(),
                        mGolsAdversarioAddResultadoEditText.getText().toString(),
                        mAdversarioAddResultadoEditText.getText().toString(),
                        mGolsMarcadoresAddResultadoEditText.getText().toString());

                //adiciona os novos dados do objeto resultadoFirebase para o database
                mResultadoDatabaseReference.push().setValue(resultadoFirebase);

                // Clear input box
                mAnoAddResultadoEditText.setText("");
                mIdAddResultadoEditText.setText("");
                mDataAddResultadoEditText.setText("");
                mGolsStaAddResultadoEditText.setText("");
                mGolsAdversarioAddResultadoEditText.setText("");
                mAdversarioAddResultadoEditText.setText("");
                mGolsMarcadoresAddResultadoEditText.setText("");
                finish();
            }
        });

        //cria o listener de autenticação de usuários
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //informa se o user está online no momento
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //usuário conectado
                    Toast.makeText(AdicionarResultado.this, "Você está conectado!", Toast.LENGTH_SHORT).show();//exibe essa mensagem se o usuário estiver conectado
                } else {
                    //usuário desconectado
                    //se estiver desconectado executa os métodos abaixo para conectar
                    //user desconetado
                    //se estiver desconectado executa os métodos abaixo para conectar
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Conectado!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Conexão cancelada!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adicionar_resultado_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
