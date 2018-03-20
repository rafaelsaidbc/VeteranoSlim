package rafasaid.com.br.santacruzveterano.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rafasaid.com.br.santacruzveterano.R;

public class CalendarioShare extends AppCompatActivity {

    //instâncias de autenticação
    public static final int RC_SIGN_IN = 1;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database

    //para cada referência que for utilizar a database, deve ter
    //uma classe
    private DatabaseReference mCalendarioDatabaseReference;//classe que faz referência a uma parte específica da Database;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //ToDo compartilhamento com Facebook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario_share);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database

        //recupera os dados que foram armazenados na activity CalendarioAdapter, usa-se final para
        //dar certo
        final Intent intent = getIntent();

        //cria uma String com nome dataCalendario, intent recupera os dados armazenados em outra
        // activity utilizando getStringExtra [nome com o qual a variável foi armazenada na outra
        //activity, neste caso dataCalendarioIntent]
        final String dataCalendario = intent.getStringExtra("dataCalendarioIntent");
        //cria um TextView dataCalendarioShareTextView que irá mostrar os dados recuperados da outra
        //activity, buscando pelo findViewById o local onde será mostrada, neste caso data_calendario_share
        TextView dataCalendarioShareTextView = (TextView) findViewById(R.id.data_calendario_share);
        //faz o dataCalendarioShareTextView mostrar o texto armazenado pela variável dataCalendario
        dataCalendarioShareTextView.setText(dataCalendario);

        final String horaCalendario = intent.getStringExtra("horaCalendarioIntent");
        TextView horaCalendarioShareTextView = (TextView) findViewById(R.id.hora_calendario_share);
        horaCalendarioShareTextView.setText(horaCalendario);

        final String adversarioCalendario = intent.getStringExtra("adversarioCalendarioIntent");
        TextView adversarioCalendarioShareTextView = (TextView) findViewById(R.id.adversario_calendario_share);
        adversarioCalendarioShareTextView.setText(adversarioCalendario);

        final String localCalendario = intent.getStringExtra("localCalendarioIntent");
        TextView localCalendarioShareTextView = (TextView) findViewById(R.id.local_calendario_share);
        localCalendarioShareTextView.setText(localCalendario);

        //faz o Button btnShareCalendario iniciar
        Button btnShareIntent = (Button) findViewById(R.id.btnShareCalendario);
        btnShareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ao clicar o Button cria uma nova Intent, que permitirá compartilhar os dados pela
                //ação Intent.ACTION_SEND
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                //cria a String dadosCalendario com as informações que serão passadas, pode-se colocar
                //várias informações de texto desde que se use o + [mais], o "\n" faz uma quebra de
                //parágrafo nas informações que serão repassadas
                String dadosCalendario = "Tem jogo marcado para dia " + dataCalendario + " às " +
                        horaCalendario + "\n" + "Santa Cruz " + " x " + adversarioCalendario + "\n" +
                        "O jogo será em " + localCalendario;
                //determina o tipo de informação que será passada, nesse caso texto
                shareIntent.setType("text/plain");
                //coloca as informações da String dataCalendario na Intent shareIntent
                shareIntent.putExtra(Intent.EXTRA_TEXT, dadosCalendario);
                //inicia a atividade dando um menu de opções de compartilhamento, os apps compatíveis
                //serão definidos pelo que está instalado no dispositivo que está realizando o compartilhamento
                //das informações
                startActivity(Intent.createChooser(shareIntent, "Compartilhar com:"));
            }
        });

        //cria o listener de autenticação de usuários
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //informa se o user está online no momento
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //usuário conectado
                    Toast.makeText(CalendarioShare.this, "Você está conectado!", Toast.LENGTH_SHORT).show();//exibe essa mensagem se o usuário estiver conectado
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
        inflater.inflate(R.menu.calendario_deletar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.atualizarCalendario:
                break;

            case R.id.deletarCalendario:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
